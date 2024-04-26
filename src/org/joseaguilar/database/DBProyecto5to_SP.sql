use DBProyecto5to_DDL;

-- ----------------- CRUD ----------------- --
-- ******** Cargos ******** --
DELIMITER $$
create procedure sp_agregarCargo(nomCar varchar(30), desCar varchar(100))
BEGIN
	insert into Cargos(nombreCargo, descripcionCargo) values
		(nomCar, desCar);
END $$
DELIMITER ;

-- call sp_agregarCargo('Carga','de medio timepo');

DELIMITER $$
create procedure sp_listarCargo()
BEGIN
	select
		Cargos.nombreCargo,
		Cargos.descripcionCargo
		from Cargos;
END $$
DELIMITER ;

-- call sp_listarCargo();

DELIMITER $$
create procedure sp_eliminarCargo(carId INT)
BEGIN
	delete
		from Cargos
		where cargoId = carId;
END $$
DELIMITER ;

 -- call sp_eliminarCargo(1);

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

-- call sp_buscarCargo(1);

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

-- call sp_editarCargo (1, 'XXXX', 'XXXXXXXXXX');


-- ******** Empleados ******** --
DELIMITER $$
create procedure sp_agregarEmpleado(nomEmp varchar(30), apeEmp varchar(30), sue decimal(10,2), horEnt time, horSal time, carId int)
BEGIN
	insert into Empleados(nombreEmpleado,apellidoEmpleado,sueldo,horaEntrada,horaSalida,cargoId) values
		(nomEmp, apeEmp, sue, horEnt, horSal, carId);
END $$
DELIMITER ;

-- CALL sp_agregarEmpleado('Manuel', 'Pérez', 2500.00, '09:00:00', '18:00:00', 2 );

DELIMITER $$
create procedure sp_listarEmpleado()
BEGIN
	select
		Empleados.nombreEmpleado,
        Empleados.apellidoEmpleado,
        Empleados.sueldo,
        Empleados.horaEntrada,
        Empleados.horaSalida,
        Empleados.cargoId,
        Empleados.encargadoId
        from Empleados;
END $$
DELIMITER ;

-- call sp_listarEmpleado();

DELIMITER $$
create procedure sp_eliminarEmpleado(empId INT)
BEGIN
	delete
		from Empleados
        where empleadoId = empId;
END $$
DELIMITER ;

-- call sp_eliminarEmpleado(3);

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

-- call sp_buscarEmpleado(1);

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

-- call sp_editarEmpleado (2, 'pete', 'chunza', 100.90, '01:00:00' , '09:00:00', 3,2);

-- ******** Clientes ******** --
DELIMITER $$
create procedure sp_agregarCliente(in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit_ varchar(15))
BEGIN
	insert into Clientes (nombre, apellido, telefono, direccion, nit) values
		(nom, ape, tel, dir, nit_);
END $$
DELIMITER ;

-- call sp_agregarCliente('Joshua', 'ok', '2023245', 'zona 1 7-12212', '20233356');

DELIMITER $$
create procedure sp_listarCliente()
BEGIN
	select
		Clientes.nombre,
        Clientes.apellido,
        Clientes.telefono,
        Clientes.direccion,
        Clientes.nit
        from Clientes;
END $$
DELIMITER ;

-- call sp_listarCliente();

DELIMITER $$
create procedure sp_buscarCliente(cliId int)
BEGIN
	select
		Clientes.nombre,
        Clientes.apellido,
        Clientes.telefono,
        Clientes.direccion,
        Clientes.nit
        from Clientes
		where clienteId = cliId;
END $$
DELIMITER ;

-- call sp_buscarCliente(1);

DELIMITER $$
create procedure sp_eliminarCliente(in cliId int)
BEGIN
	delete 
		from Clientes
		where clienteId = cliId;
END $$
DELIMITER ;

-- call sp_eliminarCliente(2);

DELIMITER $$
create procedure sp_editarCliente(in cliId int, in nom varchar(40),in ape varchar(40),in tel varchar(15),in dir varchar(150),in nit varchar(15))
BEGIN
	update Clientes
		set 
		nombre = nom,
		apellido = ape,
		telefono = tel,
		direccion = dir, 
		nit = nit
		where clienteId = cliId;
END $$
DELIMITER ;

-- CALL sp_editarCliente(1,'Jos', 'o', '202345', 'zona  7-12212', '202356');

-- ******** Facturas ******** --
DELIMITER $$
create procedure sp_agregarFactura(fec date, hor time, cliId int, empId int)
BEGIN
	insert into Facturas(fecha, hora, clienteId, empleadoId) values
		(fec, hor, cliId, empId);
END $$
DELIMITER ;

-- call sp_agregarFactura('2024-04-21', '08:30:00', 2, 4);

DELIMITER $$
create procedure sp_listarFactura()
BEGIN
	select
		Facturas.fecha,
		Facturas.hora,
        Facturas.clienteId,
        Facturas.empleadoId
		from Facturas;
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

-- call sp_eliminarFactura(3);

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

-- call sp_buscarFactura(6);

DELIMITER $$
create procedure sp_editarFactura(facId INT,fec date, hor time, cliId int, empId int)
BEGIN
	update Facturas
		set
			fecha = fec,
			hora = hor,
            clienteId = cliId,
            empleadoId = empId
			where facturaId = facId;
END $$
DELIMITER ;

--  call sp_editarFactura (6,'2024-04-10', '01:30:00', 2, 4);

-- ******** TicketSoporte ******** --
DELIMITER $$
create procedure sp_agregraTicketSoporte(desTic varchar (250), est varchar (30), in cliId int )
BEGIN
	select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
			CONCAT('Id: ', C.clienteId, ' | ', C.nombre, ' ', C.apellido) AS 'cliente',
            TS.facturaId from TicketSoporte TS
    join Clientes C on TS.clienteId = C.clienteId;
END $$
DELIMITER ;
 
-- call sp_agregraTicketSoporte('Arrababasay', 'En tramite', 4);

DELIMITER $$
create procedure sp_listarTicketSoporte()
BEGIN
	select
		TicketSoporte.ticketSoporteId,
		TicketSoporte.descripcionTicket,
		TicketSoporte.estatus,
		TicketSoporte.clienteId
		FROM TicketSoporte;
END $$
DELIMITER ;
 
-- call sp_listarTicketSoporte();

DELIMITER $$
create procedure sp_eliminarTicketSoporte(in ticId int)
BEGIN
	delete
		from TicketSoporte
		where ticketSoporteId = ticId;
END $$
DELIMITER ;
 
--  call sp_eliminarTicketSoporte(3);

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
DELIMITER ;
 
-- call sp_buscarTicketSoporte(2);

DELIMITER $$
create procedure sp_editarTicketSoporte(in ticId int, des varchar (250), est varchar (30), in cliId int )
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

-- *********** Distribuidores ************ --
DELIMITER $$
create procedure sp_agregarDistribuidor( nomDis varchar(30), dirDis varchar(200), nitDis varchar(15), telDis varchar(15))
BEGIN
	insert into Distribuidores(nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor) values
		(nomDis, dirDis, nitDis, telDis);
END $$
DELIMITER ;

-- call sp_agregarDistribuidor('Karen', '6av 6-14', '2023365', '20233325');

DELIMITER $$
create procedure sp_listarDistribuidor()
BEGIN
	select
		Distribuidores.distribuidorId,
		Distribuidores.nombreDistribuidor,
		Distribuidores.direccionDistribuidor,
		Distribuidores.nitDistribuidor,
		Distribuidores.telefonoDistribuidor
		from Distribuidores;
    END $$
DELIMITER ;

-- call sp_listarDistribuidor();

DELIMITER $$
create procedure sp_buscarDistribuidor(in disId int)
BEGIN
	select
		Distribuidores.distribuidorId,
		Distribuidores.nombreDistribuidor,
		Distribuidores.direccionDistribuidor,
		Distribuidores.nitDistribuidor,
		Distribuidores.telefonoDistribuidor
		from Distribuidores
		where distribuidorId = disId;
END $$
DELIMITER ;

-- call sp_buscarDistribuidor(1);

DELIMITER $$
create procedure sp_eliminarDistribuidor(in disId int)
BEGIN
	delete
		from Distribuidores
			where distribuidorId = disId;
END $$
DELIMITER ;

-- call sp_eliminarDistribuidor(8);

DELIMITER $$
create procedure sp_editarDistribuidor(in disId int, in nomDis varchar(30), in dirDis varchar(200), in nitDis varchar(15), in telDis varchar(15), in web varchar(50))
BEGIN
	update Distribuidores
		set
			nombreDistribuidor = nomDis,
			direccionDistribuidor = dirDis,
			nitDistribuidor = nitDis,
			telefonoDistribuidor = telDis,
			web = web
				where distribuidorId = disId;
    END $$
DELIMITER ;

-- call sp_editarDistribuidor(1,'Brandon', '5florida', '666666' , '9999999', 'WEB');

-- *********** Categoria Productos *********** --
DELIMITER $$
create procedure sp_agregarCategoriaProducto(nombCat varchar(30), desCat varchar(100))
BEGIN
	insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
		(nombCat, desCat);
END $$
DELIMITER ;

-- call sp_agregarCategoriaProducto('Azucares' , 'es dulce');

DELIMITER $$
create procedure sp_listarCategoriaProducto()
	BEGIN
		select
			CategoriaProductos.categoriaProductosId,
            CategoriaProductos.nombreCategoria,
            CategoriaProductos.descripcionCategoria
				from CategoriaProductos;
    END $$
DELIMITER ;

-- call sp_listarCategoriaProducto();

DELIMITER $$
create procedure sp_buscarCategoriaProducto(in catPId int)
BEGIN
	select 
		CategoriaProductos.categoriaProductosId,
		CategoriaProductos.nombreCategoria,
		CategoriaProductos.descripcionCategoria
			from CategoriaProductos
			where categoriaProductosId = catPId;
END $$            
DELIMITER ;

-- call sp_buscarCategoriaProducto(1);

DELIMITER $$
create procedure sp_eliminarCategoriaProducto(in catPId int)
BEGIN
	delete
		from CategoriaProductos
			where categoriaProductosId = catPId;
END $$
DELIMITER ;

-- call sp_eliminarCategoriaProducto(5);


DELIMITER $$
create procedure sp_editarCategoriaProducto(in catPId int, in nombCat varchar(30), in desCat varchar(100))
BEGIN
	update CategoriaProductos
		set
			nombreCategoria = nombCat,
			descripcionCategoria = desCat
				where categoriaProductosId = catPId;
END $$
DELIMITER ;

-- call sp_editarCategoriaProducto(1, 'ponche' , 'es dulce');

-- --- ************** Productos *************** --
DELIMITER $$
create procedure sp_agregarProducto(in nomPro varchar(50),in desPro varchar(100),in canSto int, in preVenUni decimal(10,2),in preVenMay decimal(10,2),in preCom decimal(10,2), in disId int, in catProId int)
BEGIN
	insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, distribuidorId, categoriaProductosId ) values
		(nomPro, desPro, canSto, preVenUni, preVenMay, preCom, disId, catProId);
END $$
DELIMITER ;

-- call sp_agregarProducto('Cereal', 'Es comestible', 50, 50.15, 60.20, 55.90, 1,1);

DELIMITER $$
create procedure sp_listarProducto()
BEGIN 
	select
			Productos.nombreProducto,
            Productos.descripcionProducto,
            Productos.cantidadStock,
            Productos.precioVentaUnitario,
            Productos.precioVentaMayor,
            Productos.precioCompra,
            Productos.imagenProducto,
            Productos.distribuidorId,
            Productos.categoriaProductosId
				from Productos;
END $$
DELIMITER ;

-- call sp_listarProducto();

DELIMITER $$
create procedure sp_buscarProducto(in proId int)
BEGIN 
	select
			Productos.nombreProducto,
            Productos.descripcionProducto,
            Productos.cantidadStock,
            Productos.precioVentaUnitario,
            Productos.precioVentaMayor,
            Productos.precioCompra,
            Productos.imagenProducto,
            Productos.distribuidorId,
            Productos.categoriaProductosId
            from Productos
			where productoId = proId;
END $$
DELIMITER ;

-- call sp_buscarProducto(1);

DELIMITER $$
create procedure sp_eliminarProducto(in proId int)
BEGIN
	delete from Productos
		where productoId = proId;
END $$
DELIMITER ;

-- call sp_eliminarProducto(4);

DELIMITER $$
create procedure sp_editarProducto(in proId int,in nomPro varchar(50),in desPro varchar(100),in canSto int, in preVenUni decimal(10,2),in preVenMay decimal(10,2),in preCom decimal(10,2), in disId int, in catProId int)
BEGIN
	update Productos	
		set 
		nombreProducto = nomPro,
		descripcionProducto = desPro,
		cantidadStock = canSto,
		precioVentaUnitario = preVenUni,
		precioVentaMayor = preVenMay,
		precioCompra = preCom,
		distribuidorId = disId,
		categoriaProductosId = catProId
		where productoId = proId;
END $$
DELIMITER ;

-- call sp_editarProducto(1,'Ojuelas', 'Es comestible', 50, 50.15, 60.20, 55.90, 1,1);

-- *********** Compras ************** --
DELIMITER $$
create procedure sp_agregarCompra(in fecCom date, in totCom decimal (10,2))
BEGIN 
	insert into Compras (fechaCompra, totalCompra) values
		(fecCom, totCom);
END $$
DELIMITER ;

-- call sp_agregarCompra('2024-04-10', 500.00);

DELIMITER $$
create procedure sp_listarCompra()
BEGIN
	select * from Compras;
END $$
DELIMITER ;

-- call sp_listarCompra();

DELIMITER $$
create procedure sp_buscarCompra(in comId int)
BEGIN	
	select * from Compras 
		where compraId = comId;
END $$
DELIMITER ;

-- call sp_buscarCompra(1);

DELIMITER $$
create procedure sp_eliminarCompra(in comId int)
BEGIN 
	delete from Compras
	where compraId = comId;
END $$
DELIMITER ;

-- call sp_eliminarCompra(3);

DELIMITER $$
create procedure sp_editarCompra(in comId int,in fecCom date,in totCom decimal (10,2))
BEGIN 
	update Compras
		set 
			fechaCompra = fecCom,
			totalCompra = totCom
			where compraId = comId;
END $$
DELIMITER ;

-- call sp_editarCompra(2,'2022-01-10', 100.00);


-- *********** DetalleProductos ********** --
DELIMITER $$
create procedure sp_agregarDetalleCompra(in canCom int, in proId int, in comId int)
BEGIN
	insert into DetalleCompra(cantidadCompra, productoId, compraId) values
		(canCom, proId, comId);
END $$
DELIMITER ;

-- call sp_agregarDetalleCompra(100,1,1);

DELIMITER $$
create procedure sp_listarDetalleCompra()
BEGIN
	select
		D.detalleCompraId,
		D.cantidadCompra,
		D.productoId,
		D.compraId
			from DetalleCompra D;
END $$
DELIMITER ;

-- call sp_listarDetalleCompra();

DELIMITER $$
create procedure sp_buscarDetalleCompra(in detCId int)
BEGIN
	select * from DetalleCompra
			where detalleCompraId = detCId;
END $$
DELIMITER ;

-- call sp_buscarDetalleCompra(1);

DELIMITER $$
create procedure sp_eliminarDetalleCompra(in detCId int)
BEGIN
	delete
		from DetalleCompra
			where detalleCompraId = detCId;
END $$
DELIMITER ;

-- call sp_eliminarDetalleCompra(3);

DELIMITER $$
create procedure sp_editarDetalleCompra(in detCId int, in canCom int, in proId int, in comId int)
BEGIN
	update DetalleCompra
		set
			cantidadCompra = canCom,
			productoId = proId,
			compraId = comId
				where detalleCompraId = detCId;
END $$
DELIMITER ;

-- call sp_editarDetalleCompra(2,100,1,2);

-- ***************** Promociones ****************** --
DELIMITER $$
create procedure sp_agregarPromociones(prePro decimal (10,2), desPro varchar (200), fecIni date, fecFin date, in proId int)
BEGIN
	insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId )values
		(prePro,desPro, fecIni, fecFin, proId);
END $$
DELIMITER ;
 
-- call sp_agregarPromociones(600, 'Es 2x1 aproveche', '2024-04-23','2024-04-29',1);
 
DELIMITER $$
create procedure sp_listarPromociones()
BEGIN
	select
		Promociones.promocionId,
		Promociones.precioPromocion,
		Promociones.fechaInicio,
		Promociones.fechaFinalizacion,
		Promociones.productoId
			FROM Promociones;
END $$
DELIMITER ;
 
-- call sp_listarPromociones();
 
DELIMITER $$
create procedure sp_eliminarPromociones(in proId int)
BEGIN
	delete
		from Promociones
		where promocionId = proId;
END $$
DELIMITER ;

-- call sp_eliminarPromociones(3);
 
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
DELIMITER ;

-- call sp_buscarPromociones(2);
 
DELIMITER $$
create procedure sp_editarPromociones(in promId int, prePro decimal (10,2), desPro varchar (200), fecIni date, fecFin date, in proId int )
BEGIn
	update Promociones
		set
			precioPromocion = prePro,
			descripcionPromocion = desPro,
			fechaInicio = fecIni,
			fechaFinalizacion = fecFin,
			productoId = proId
			where promocionId = promId;
END $$
DELIMITER ;

-- call sp_editarPromociones(2, 200, 'Es 4*1 apro', '2024-04-19','2024-04-25',1);

-- ******************* DetalleFacturas ************* --

DELIMITER $$
create procedure sp_agregarDetalleFactura(in factId int, in prodId int)
BEGIN
	insert into DetalleFactura(facturaId, productoId) values
		(factId, prodId);
END $$
DELIMITER ;

-- call sp_agregarDetalleFactura(1,1);

DELIMITER $$
create procedure sp_listarDetalleFactura()
BEGIN
	select 
		DetalleFactura.detalleFacturaId,
        DetalleFactura.facturaId,
        DetalleFactura.productoId
			from DetalleFactura;
END $$
DELIMITER ;

-- call sp_listarDetalleFactura();

DELIMITER $$
create procedure sp_eliminarDetalleFactura(in detId int)
BEGIN
	delete
		from DetalleFactura
			where detalleFacturaId = detId;
END $$
DELIMITER ;

-- call sp_eliminarDetalleFactura(2);

DELIMITER $$
create procedure sp_buscarDetalleFactura(in detId int)
BEGIN
	select 
		DetalleFactura.detalleFacturaId,
        DetalleFactura.facturaId,
        DetalleFactura.productoId
			from DetalleFactura
			where detalleFacturaId = detId;
END $$
DELIMITER ;

-- call sp_buscarDetalleFactura(1);

DELIMITER $$
create procedure sp_editarDetalleFactura(in detId int, in factId int, in prodId int)
BEGIN
	update DetalleFactura
		set 
			facturaId = factId,
            productoId = prodId
            where detalleFacturaId = detId;
END $$
DELIMITER ;

-- call sp_editarDetalleFactura(1,2,1);