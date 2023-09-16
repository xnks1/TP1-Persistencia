package com.utn.practico1;
import com.utn.practico1.emuns.EstadoPedido;
import com.utn.practico1.emuns.FormaPago;
import com.utn.practico1.emuns.TipoEnvio;
import com.utn.practico1.emuns.TipoProducto;
import com.utn.practico1.entidades.*;
import com.utn.practico1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.hibernate.Hibernate;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class Practico1Application {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	RubroRepository rubroRepository;


	public static void main(String[] args) {
		SpringApplication.run(Practico1Application.class, args);

		System.out.println("TEST TP1 - Funcionando");
	}
	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo, PedidoRepository pedidoRepo, RubroRepository rubroRepo,
			DetallePedidoRepository detallePedidoRepo, ProductoRepository productoRepo) {
		return args -> {
			System.out.println("TEST TP1 -Inicializando...");

			//Fechas
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaString= "22/12/2022";
			String fechaString2= "23/12/2022";
			Date fechaPedido = dateFormat.parse(fechaString);
			Date fechaFactura = dateFormat.parse(fechaString2);


			//Rubro////////////////////////////////////////////
			Rubro rubro1 = Rubro.builder()
					.denominacion("Arroz")
					.build();



			//Producto////////////////////////////////////////////

			Producto producto1 = Producto.builder()
					.tipoProducto(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(10)
					.denominacion("Arroz")
					.precioVenta(100.50)
					.precioCompra(200.32)
					.stockActual(10)
					.stockMinimo(5)
					.unidadMedida("Kg")
					.receta("Receta")
					.build();

			Producto producto2 = Producto.builder()
					.tipoProducto(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Hamburguesa")
					.precioVenta(200.00)
					.precioCompra(300.00)
					.stockActual(10)
					.stockMinimo(5)
					.unidadMedida("unidad2")
					.receta("Receta2")
					.build();

			//Agregar producto a rubro//
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			rubroRepository.save(rubro1);


			//Cliente////////////////////////////////////////////
			Cliente cliente1 = Cliente.builder()
					.nombre("Juan")
					.apellido("Perez")
					.telefono("12345678")
					.email("j.perez@utn")
					.build();


			// Domicilio////////////////////////////////////////////
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Brazil")
					.localidad("San Martin de Porres")
					.numero("1")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("San Martin ")
					.localidad("Bermejo")
					.numero("2")
					.build();


			//agregar domicilio a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			//DetallePedido////////////////////////////////////////////
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(500.00)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(1000.0)
					.build();

			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(1500.0)
					.build();

			//Pedido////////////////////////////////////////////
			Pedido pedido1 = Pedido.builder()
					.estado(EstadoPedido.INICIADO)
					.fecha(fechaPedido)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(500.00)
					.build();


			Pedido pedido2 = Pedido.builder()
					.estado(EstadoPedido.ENTREGADO)
					.fecha(fechaPedido)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(2500.00)
					.build();


			// Factura////////////////////////////////////////////
			Factura factura1 = Factura.builder()
					.numero(1)
					.fecha(fechaFactura)
					.descuento(100.00)
					.formaPago(FormaPago.EFECTIVO)
					.total(2000)
					.build();
			Factura factura2 = Factura.builder()
					.numero(2)
					.fecha(fechaFactura)
					.descuento(100.00)
					.formaPago(FormaPago.TARJETA)
					.total(2000)
					.build();

			//agregar detalle a pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido2.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			//agregar pedido a cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			//DetallePedido set a producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);

			//Factura set a pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//Guardar los objetos en la base de datos
			clienteRepository.save(cliente1);

			//recuperar objeto rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}

			//recuperar objeto cliente desde la base de datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}


		};

	}



}
