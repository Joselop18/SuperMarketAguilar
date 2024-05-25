use SuperKinalDB;

-- ----------------------- CLIENTES ----------------------------

-- Agregar
delimiter $$
create procedure sp_agregarCliente(in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit varchar(15))
	begin
		insert into Clientes (nombre, apellido, telefono, direccion, nit) values
			(nom, ape, tel, dir, nit);
    end $$
delimiter ;


-- listar
delimiter $$
create procedure sp_listarCliente()
	begin
		select * from Clientes;
    end $$
delimiter ;


-- buscar
delimiter $$
create procedure sp_buscarCliente(in cliId int)
	begin
		select * from Clientes
			where clienteId = cliId;
    end $$
delimiter ;


-- eliminar
delimiter $$
create procedure sp_eliminarCliente(in cliId int)
	begin
		delete from Clientes
        where clienteId = cliId;
    end $$
delimiter ;

-- editar
delimiter $$
create procedure sp_editarCliente(in cliId int, in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit varchar(15))
	begin
		update Clientes
			set 
            nombre = nom,
            apellido = ape,
            telefono = tel,
            direccion = dir, 
            nit = nit
            where clienteId = cliId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- *** Cargos *** --
DELIMITER $$
create procedure sp_agregarCargo(nomCar varchar(30), desCar varchar(100))
BEGIN
	insert into Cargos(nombreCargo, descripcionCargo) values
		(nomCar, desCar);
END $$
DELIMITER ;

call sp_agregarCargo('Supervisor', 'Supervisa');

DELIMITER $$
create procedure sp_listarCargo()
BEGIN
	select * from Cargos; 
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_eliminarCargo(carId INT)
BEGIN
	delete
		from Cargos
		where cargoId = carId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarCargo(carId INT)
BEGIN
	select
		Cargos.nombreCargo,
		Cargos.descripcionCargo
		from Cargos
		where cargoId = carId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarCargo(carId INT, nomCar varchar(30), desCar varchar(100))
BEGIN
	update Cargos
		set
			nombreCargo = nomCar,
			descripcionCargo = desCar
			where cargoId = carId;
END $$
DELIMITER ;

-- ==========================================================================================================================================================================
-- --------------------------------------------------------Compras-------------------------------------------------------------------- 

-- agregar
delimiter $$
create procedure sp_agregarCompra(in fecCom date, in totCom decimal (10,2))
	begin 
		insert into Compras (fechaCompra, totalCompra) values
			(fecCom, totCom);
    end $$
delimiter ;

-- listar
delimiter $$
create procedure sp_listarCompra()
	begin
		select * from Compras;
    end $$
delimiter ;

-- buscar
delimiter $$
create procedure sp_buscarCompra(in comId int)
	begin	
		select * from Compras 
			where compraId = comId;
    end $$
delimiter ;

-- eliminar 
delimiter $$
create procedure sp_eliminarCompra(in comId int)
	begin 
		delete from Compras
        where compraId = comId;
    end $$
delimiter ;

-- editar
delimiter $$
create procedure sp_editarCompra(in comId int,in fecCom date,in totCom decimal (10,2))
	begin 
		update Compras
			set 
				fechaCompra = fecCom,
                totalCompra = totCom
                where compraId = comId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- Agregar
delimiter $$
create procedure sp_agregarDistribuidor(in nomD varchar(30), in dirD varchar(200), in nitD varchar(15), in telD varchar(15), in web varchar(50))
	begin
		insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web) values
			(nomD, dirD, nitD, telD, web);
    end $$
delimiter ;

-- Listar
delimiter $$
create procedure sp_listarDistribuidor()
	begin
		select * from Distribuidores;
    end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscarDistribuidor(in disId int)
	begin
		select * from Distribuidores
			where distribuidorId = disId;
    end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminarDistribuidor(in disId int)
	begin
		delete
			from Distribuidores
				where distribuidorId = disId;
    end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editarDistribuidor(in disId int, in nomD varchar(30), in dirD varchar(200), in nitD varchar(15), in telD varchar(15), in web varchar(50))
	begin
		update Distribuidores
			set
				nombreDistribuidor = nomD,
                direccionDistribuidor = dirD,
                nitDistribuidor = nitD,
                telefonoDistribuidor = telD,
                web = web
					where distribuidorId = disId;
    end $$
delimiter ;

-- *** Empleados *** --
DELIMITER $$
create procedure sp_agregarEmpleado(nomEmp varchar(30), apeEmp varchar(30), sue decimal(10,2), horEnt time, horSal time, carId int, encarId int)
BEGIN
	insert into Empleados(nombreEmpleado,apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargoId, encargadoId) values
		(nomEmp, apeEmp, sue, horEnt, horSal, carId, encarId);
END $$
DELIMITER ;

call sp_agregarEmpleado('Jose', 'Aguilar', 5000.00, '07:00:00', '15:00:00', 1, null);

DELIMITER $$
create procedure sp_listarEmpleado()
BEGIN
	select*from Empleados;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_eliminarEmpleado(empId INT)
BEGIN
	delete
		from Empleados
        where empleadoId = empId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarEmpleado(empId INT)
BEGIN
	select
		Empleados.nombreEmpleado,
        Empleados.apellidoEmpleado,
        Empleados.sueldo,
        Empleados.horaEntrada,
        Empleados.horaSalida,
        Empleados.cargoId,
        Empleados.encargadoId
        from Empleados
        where empleadoId = empId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarEmpleado(empId INT, nomEmp varchar(30), apeEmp varchar(30), sue decimal(10,2), horEnt time, horSal time, carId int, encId int)
BEGIN
	update Empleados
		set
			nombreEmpleado = nomEmp,
            apellidoEmpleado = apeEmp,
            sueldo = sue,
            horaEntrada = horEnt,
        	horaSalida = horSal,
			cargoId = carId,
            encargadoId = encId
            where empleadoId = empId;
END $$
DELIMITER ;

-- ==========================================================================================================================================================================
-- 2
-- =====================================================================================================================
-- Agregar
delimiter $$
create procedure sp_agregarCategoriaProductos(in nombC varchar(30), descC varchar(100))
	begin
		insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
			(nombC, descC);
    end $$
delimiter ;

-- Listar
delimiter $$
create procedure sp_listarCategoriaProducto()
	begin
		select
			C.categoriaProductosId,
            C.nombreCategoria,
            C.descripcionCategoria
				from CategoriaProductos C;
    end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscarCategoriaProducto(in catPId int)
	begin
		select * from CategoriaProductos
			where categoriaProductoId = catPId;
    end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminarCategoriaProducto(in catPId int)
	begin
		delete
			from CategoriaProductos
				where categoriaProductoId = catPId;
    end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editarCategoriaProducto(in catPId int, in nombC varchar(30), in descC varchar(100))
	begin
		update CategoriaProductos
			set
				nombreCategoria = nombC,
                descripcionCategoria = descC
					where categoriaProductoId = catPId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================
-- ------------------------------------------------------Productos-------------------------------------------------------------------

-- agregar
delimiter $$
create procedure sp_agregarProducto(in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int)
	begin
		insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductosId ) values
			(nom, des, can, preU, preM, preC, ima, disId, catId);
	end $$
delimiter ;

-- listar
delimiter $$
create procedure sp_listarProducto()
	begin 
		select P.productoId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor, P.precioCompra, P.imagenProducto,
			D.nombreDistribuidor as 'distribuidor', 
            CP.nombreCategoria as 'categoria' from Productos P
            join Distribuidores D on P.distribuidorId =  D.distribuidorId
            join CategoriaProductos CP on P.categoriaProductosId = CP.categoriaProductosId;
    end $$
delimiter ;

-- buscar
delimiter $$
create procedure sp_buscarProducto(in proId int)
	begin 
		select * from Productos
        where productoId = proId;
    end $$
delimiter ;

-- eliminar 
delimiter $$
create procedure sp_eliminarProducto(in proId int)
	begin
		delete from Productos
			where productoId = proId;
    end $$
delimiter ;

-- editar
delimiter $$
create procedure sp_editarProducto(in proId int, in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int )
	begin
		update Productos	
			set 
            nombreProducto = nom,
            descripcionProduto = des,
            cantidadStock = can,
            precioVentaUnitario = preU,
            precioVentaMayor = preM,
            precioCompra = preC,
            imagenProducto = ima,
            distribuidorId = disId,
            categoriaProductosId = catId
            where productoId = proId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================

-- 3
-- ===========================================================================================================
-- Agregar
delimiter $$
create procedure sp_agregarDetalleCompra(in canC int, in proId int, in comId int)
	begin
		insert into DetalleCompras(cantidadCompra, productoId, compraId) values
			(canC, proId, comId);
    end $$
delimiter ;

-- listar
delimiter $$
create procedure sp_listarDetalleCompra()
	begin
		select
			D.detalleCompraId,
            D.cantidadCompra,
            D.productoId,
            D.compraId
				from DetalleCompras D;
    end $$
delimiter ;

-- Buscar
delimiter $$
create procedure sp_buscarDetalleCompra(in detCId int)
	begin
		select * from DetalleCompras
				where detalleCompraId = detCId;
    end $$
delimiter ;

-- Eliminar
delimiter $$
create procedure sp_eliminarDetalleCompra(in detCId int)
	begin
		delete
			from DetalleCompras
				where detalleCompraId = detCId;
    end $$
delimiter ;

-- Editar
delimiter $$
create procedure sp_editarDetalleCompra(in detCId int, in canC int, in proId int, in comId int)
	begin
		update DetalleCompras
			set
				cantidadCompra = canC,
                productoId = proId,
                compraId = comId
					where detalleCompraId = detCId;
    end $$
delimiter ;

-- ==========================================================================================================================================================================

-- *** Facturas *** --
DELIMITER $$
create procedure sp_agregarFactura(fe date, ho time, cliId int, empId int, tot decimal(10, 2))
BEGIN
	insert into Facturas(fecha, hora, clienteId, empleadoId, total) 
    values(fe, ho, cliId, empId, tot);
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_listarFactura()
BEGIN
	select F.facturaId, F.fecha, F.hora, F.total,
        C.nombre as 'cliente',
        E.nombreEmpleado as 'empleado' from Facturas F
        join Clientes C on C.clienteId = F.clienteId
        join Empleados E on E.empleadoId = F.empleadoId;
END $$
DELIMITER ;

call sp_listarFactura();
DELIMITER $$
create procedure sp_eliminarFactura(facId INT)
BEGIN
	delete
		from Facturas
		where facturaId = facId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarFactura(in facId INT)
BEGIN
	select
		Facturas.fecha,
		Facturas.hora,
        Facturas.clienteId,
        Facturas.empleadoId
		from Facturas
		where facturaId = facId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_editarFactura(facId INT, cliId int, empId int, tot decimal(10, 2))
BEGIN
	update Facturas
		set
            clienteId = cliId,
            empleadoId = empId,
            total = tot
			where facturaId = facId;
END $$
DELIMITER ;

-- ==========================================================================================================================================================================

-- ------------------ Detalle Factura ---------------------------
DELIMITER $$
create procedure sp_AgregarDetalleFactura(in factId int, in prodId int)
begin
	insert into DetalleFactura(facturaId, productoId) values
		(factId, prodId);
end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_ListarDetalleFactura()
begin
	select 
		DetalleFactura.detalleFacturaId,
        DetalleFactura.facturaId,
        DetalleFactura.productoId
			from DetalleFactura;
end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_EliminarDetalleFactura(in detId int)
begin
	delete
		from DetalleFactura
			where detalleFacturaId = detId;
end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_BuscarDetalleFactura(in detId int)
begin
	select 
		DetalleFactura.detalleFacturaId,
        DetalleFactura.facturaId,
        DetalleFactura.productoId
			from DetalleFactura
			where detalleFacturaId = detId;
end $$
DELIMITER ;
 
DELIMITER $$
create procedure sp_EditarDetalleFactura(in detId int, in factId int, in prodId int)
begin
	update DetalleFactura
		set 
			facturaId = factId,
            productoId = prodId
            where detalleFacturaId = detId;
end $$
DELIMITER ;

-- ==========================================================================================================================================================================

-- AGREGAR TIKETSOPORTE
DELIMITER $$
create procedure sp_agregarTicketSoporte(des varchar (250), in cliId int,in facId int )
	BEGIN
		insert into TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)values
			(des, 'Recien Creado', cliId, facId);
	END $$
DELIMITER ;
 
-- call sp_agregarTicketSoporte('Arrababasay', 2, 1);
 
-- listar TIKETSOPORTE
DELIMITER $$
create procedure sp_listarTicketSoporte()
	BEGIN
		select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
				concat('Id: ', C.clienteId, ' - ', C.nombre, ' ', C.apellido) as 'Cliente',
                concat('Id: ', F.facturaId, ' - ', F.fecha, ' - ', F.hora, ' - ', F.total) as 'Factura' from TicketSoporte TS
		join Clientes C on TS.clienteId = C.clienteId
        join Facturas F on TS.facturaId = F.facturaId;
	END $$
DELIMITER ;
 
call sp_listarTicketSoporte();
 
-- Eliminar TIKETSOPORTE
DELIMITER $$
create procedure sp_eliminarTicketSoporte(in ticId int)
	BEGIN
		delete
			from TicketSoporte
				where ticketSoporteId = ticId;
	END $$
DELIMITER ;
 
-- call sp_eliminarTicketSoporte(1);
 
-- Buscar TIKETSOPORTE
DELIMITER $$
create procedure sp_buscarTicketSoporte(in ticId int)
	BEGIN
		select
			TicketSoporte.ticketSoporteId,
			TicketSoporte.descripcionTicket,
			TicketSoporte.estatus,
			TicketSoporte.clienteId
				from TicketSoporte
					where ticketSoporteId = ticId;
	END $$
DELIMITER  ;
 
call sp_buscarTicketSoporte(2);
 
-- Editar TIKETSOPORTE
DELIMITER $$
create procedure sp_editarTicketSoporte(in ticId int, des varchar (250), est varchar (30), in cliId int, in facId int)
	BEGIN
		update TicketSoporte
			set
				descripcionTicket = des,
				estatus = est,
				clienteId = cliId
					where ticketSoporteId = ticId;
	END $$
DELIMITER ;
 
-- call sp_editarTicketSoporte(2, 'Jonathan malo', 'Perdido', 1);
-- ------------------------------------------------------------------------------------------------------

-- ==========================================================================================================================================================================

-- Agregar Promociones
DELIMITER $$
create procedure sp_agregarPromociones(pre decimal (10,2), des varchar (200), fecI date, fecF date, in proId int)
	BEGIN
		insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId )values
			(pre,des, fecI, fecF, proId);
	END $$
DELIMITER ;
 
-- call sp_agregarPromociones();
 
-- Listar Promociones
DELIMITER $$
create procedure sp_listarPromociones()
	begin
		select
			Promociones.promocionId,
			Promociones.precioPromocion,
			Promociones.fechaInicio,
			Promociones.fechaFinalizacion,
			Promociones.productoId
				from Promociones;
 
	end $$
DELIMITER ;
 
-- call sp_listarPromociones();
 
-- Eliminar Promociones
DELIMITER $$
create procedure sp_eliminarPromociones(in proId int)
	BEGIN
		delete
			from Promociones
				where promocionId = proId;
	END $$
DELIMITER ;
-- call sp_eliminarPromociones();
 
-- Buscar Promociones
DELIMITER $$
create procedure sp_buscarPromociones(in proId int)
	BEGIN
		select
			Promociones.promocionId,
			Promociones.precioPromocion,
			Promociones.descripcionPromocion,
			Promociones.fechaInicio,
			Promociones.fechaFinalizacion,
			Promociones.productoId
				from Promociones
					where promocionId = proId;
	END $$
DELIMITER  ;
-- call sp_buscarPromociones()
 
-- Editar Promociones
DELIMITER $$
create procedure sp_editarPromociones(in promId int, pre decimal (10,2), des varchar (200), fecI date, fecF date, in proId int )
	BEGIN
		update Promociones
			set
				precioPromocion = pre,
				descripcionPromocion = des,
				fechaInicio = fecI,
				fechFinalizacion = fecF,
				profuctoId = proId
					where promocionId = promId;
	END $$
DELIMITER ;
-- call sp_editarPromociones();

-- ==========================================================================================================================================================================-- ==========================================================================================================================================================================
-- ***************** Asignar Encargado **********************
 
DELIMITER $$
create procedure sp_editarEncargado(empId int, encId int)
	BEGIN
		update Empleados
			set
				encargadoId = encId
					where empleadoId = empId;
	END $$
DELIMITER ;

set global time_zone = '-6:00';

-- //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- ------------------------ Niveles Accesos -----------------------
create table NivelesAcceso(
	nivelAccesoId int not null auto_increment,
    nivelAcceso varchar(40) not null,
    primary key PK_nivelAccesoId(nivelAccesoId)
);
-- ---------------------------- Usuarios --------------------------
create table Usuarios(
	usuarioId int not null auto_increment,
    usuario varchar(50) not null,
    contrasenia varchar(100) not null,
    nivelAccesoId int not null,
    empleadoId int not null,
    primary key PK_usuarioId(usuarioId),
    constraint FK_Usuarios_NivelesAcceso foreign key Usuarios(nivelAccesoId)
		references NivelesAcceso(nivelAccesoId),
	constraint FK_Usuarios_Empleados foreign key Usuarios(empleadoId)
		references Empleados(empleadoId)
);


-- ------------ Agregar Usuario -----------
DELIMITER $$
create procedure sp_agregarUsuario(us varchar(40), con varchar(100), nivAccId int, empId int)
	BEGIN
		insert into Usuarios(usuario, contrasenia, nivelAccesoId, empleadoId)values
			(us, con, nivAccId, empId);
	END $$
DELIMITER ;

call sp_agregarUsuario('Jaguilar', '1234', 1, 2);

select*from NivelesAcceso;

-- ----------- Buscar Usuario ------------
DELIMITER $$
create procedure sp_buscarUsuario(us varchar(30))
	BEGIN
		select*from Usuarios
			where usuario = us;
	END $$
DELIMITER ;
-- --------- listarNivelAcceso -----------
DELIMITER $$
create procedure sp_listarNivelAcceso()
	BEGIN
		select*from nivelesAcceso;
	END $$
DELIMITER ;

select*from Usuarios;
select * from Empleados;