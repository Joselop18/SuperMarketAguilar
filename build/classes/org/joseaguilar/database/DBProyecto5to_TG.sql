delimiter $$
create trigger verificacionCliente
before insert on Facturas
for each row
begin
declare validacion boolean;
    set validacion = fn_verificarCliente(new.nit);
    if !validacion then
signal sqlstate '45000'
set message_text = "La factura no se puede crear, porque el cliente no existe";
    end if;
end $$
delimiter ;
delimiter $$
create function fn_verificarCliente(nt int) returns boolean deterministic
begin
declare flag boolean;
    declare verificacion int;
    set verificacion = (select count(*) from Clientes where nit = nt);
    if verificacion = 1 then
set flag = true;
else
set flag = false;
end if;
    return flag;
end $$
delimiter ;
delimiter $$
create function fn_totalFactura(facId int) returns decimal(10, 2) deterministic
begin
declare total int default 0;
        declare precio int default 0;
        declare cantidad int;
        declare producto int;
        declare limite int default 0;
        declare i  int default 0;
set producto = (select DF.productoId from DetalleFactura DF where DF.facturaId = facId);
        set precio = (select P.precioVentaUnitario from Productos P where P.productoId = producto);
        set total = (total + precio);
        set i = facId;
        total : loop
        if i = facId or i <= facId then
leave total;
end if;
        end loop;
        return total;
    end$$
delimiter ;

delimiter $$
create function fn_calcularTotalUnitario(factId int) returns decimal(10, 2) deterministic
begin
declare total decimal(10,2) default 0.0;
    declare precio decimal(10,2);
    declare i int default 1;
    declare curFacturaId, curProductoId int;
    declare cursorDetalleFactura cursor for
select DF.facturaId, DF.productoId from detalleFactura DF;
open cursorDetalleFactura;

    totalLoop : loop
    fetch cursorDetalleFactura into curFacturaId, curProductoId;
    if facId = curFacturaId then
set precio = (select P.precioVentaUnitario from Productos P where P.productoId = curProductoId);
set total = total + precio;
    end if;
    if i = (select count(*) from detalleFactura) then
leave totalLoop;
end if;
    set i = i + 1;
    end loop totalLoop;
    call sp_asignarTotal(total, facId);
    return total;
end$$
delimiter ;
delimiter $$
create function fn_calcularTotalMayor(factId int) returns decimal(10, 2) deterministic
begin
declare total decimal(10,2) default 0.0;
    declare precio decimal(10,2);
    declare i int default 1;
    declare curFacturaId, curProductoId int;
    declare cursorDetalleFactura cursor for
select DF.facturaId, DF.productoId from detalleFactura DF;
open cursorDetalleFactura;

    totalLoop : loop
    fetch cursorDetalleFactura into curFacturaId, curProductoId;
    if factId = curFacturaId then
set precio = (select P.precioVentaMayor from Productos P where P.productoId = curProductoId);
set total = total + (precio % 1.5);
    end if;
    if i = (select count(*) from detalleFactura) then
leave totalLoop;
end if;
    set i = i + 1;
    end loop totalLoop;
    call sp_asignarTotal(total, facId);
    return total;
end$$
delimiter ;

delimiter $$
create procedure sp_asignarTotal(in tot decimal(10,2), in factId int)
begin
update Facturas
set total = tot
where facturaId = facId;
end $$
delimiter ;

delimiter $$
create trigger tg_totalFactura
after insert on detalleFactura
for each row
begin
declare total decimal(10,2);
    set total = fn_calcularTotal(NEW.facturaId);
end$$
delimiter ;

delimiter $$
create procedure sp_manejoStock(in proId int)
begin
update Productos
set
cantidad = cantidad - 1
            where productoId = proId;
end$$
delimiter ;

delimiter $$
create trigger tg_restarStock
before insert on detalleFactura
for each row
begin
    if (select P.cantidadStock from Productos P where productoId = NEW.productoId) = 0 then
signal sqlstate'45000'
set message_text="No contamos con este producto en stock";
    else
call sp_manejoStock(new.productoId);
end if;
end $$
delimiter ;