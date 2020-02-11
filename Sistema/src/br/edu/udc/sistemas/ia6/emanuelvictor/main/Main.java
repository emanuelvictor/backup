package br.edu.udc.sistemas.ia6.emanuelvictor.main;



public class Main {
	public static void main(String[] args) throws Exception {
//		Object obj = null;
//		
//		//Brincando com a Reflection
//		
//		Supplier supplier = new Supplier();
//		supplier.setAddress("asdfa");
//		supplier.setCellular("asdsdfafa");
//		supplier.setCEP("asASDFASDdsdfafa");
//		supplier.setCNPJ("aASDFASDFASDsdsdfafa");
//		supplier.setCorporateName("aASDFASDFASDsdsdfafasdfasdf2342134a");
//		supplier.setStateRegistration("aASDFASDasdfasdfFASDsdsdfafa");
//		supplier.setEmail("asdfasd@asdfasdfFASDsdsdfafa");
//		supplier.setPhone("aASDFASDasdfasdfFASDsdsdfafa");
//		supplier.setFantasyName("aASDFASDasdfasdfFASDsdsdfafa");
//		supplier.setIdSupplier(11);
//		
//		Client client = new Client();
//		client.setIdClient(1);
//		client.setAddress("asdfa");
//		client.setCellular("asdsdfafa");
//		client.setPhone("aASDFASDasdfasdfFASDsdsdfafa");
//		client.setCep("asASDFASDdsdfafa");
//		client.setCpf("asASDFASDdsdfafa");
//		client.setName("asASDFASDdsdfafa");
//		client.setEmail("asdfasd@asdfasdfFASDsdsdfafa");
//		client.setRg("asdfasd@asdfasdfFASDsdsdfafa");
//		
//		Brand brand = new Brand();
//		brand.setIdBrand(12312);
//		brand.setDescription("Ford");
//		
//		Model model = new Model();
//		model.setIdModel(11);
//		model.setDescription("FOCUS");
//		model.setBrand(brand);
//		
//		Vehicle vehicle = new Vehicle();
//		vehicle.setChassis("ASDFASDFA");
//		vehicle.setPlaque("ASDFASDFA");
//		vehicle.setClient(client);
//		vehicle.setIdVehicle(123);
//		vehicle.setModel(model);
//		vehicle.setBrand(brand.getDescription());
//		
//		Vehicle vehicle3 = new Vehicle();
//		vehicle3.setChassis("ASDFASDFA");
//		vehicle3.setPlaque("ASDFASDFA");
//		vehicle3.setClient(client);
//		vehicle3.setIdVehicle(123);
//		vehicle3.setModel(model);
//		vehicle3.setBrand(brand.getDescription());
//		
//		Vehicle vehicle2 = new Vehicle();
//		vehicle2.setChassis("ASDFASDFA");
//		vehicle2.setPlaque("ASDFASDFA");
//		vehicle2.setClient(client);
//		vehicle2.setIdVehicle(123);
//		vehicle2.setModel(model);
//		vehicle2.setBrand(brand.getDescription());
//		
//		
//		
//		System.out.println("Antes");
//		System.out.println(obj);
//
//		obj = Tests.execute(client);
//		
//		Tests.printAnnotations(obj);
//		Tests.printMethods(obj);
//		
//		
//		
//		System.out.println("Depois");
//		System.out.println(obj);
//		Tests.printAnnotations(obj);
//		Tests.printMethods(obj);
		
		
		
//		Class <?> c = Brand.class;

//		DaoBrand daoBrand = new DaoBrand();
		
//		Client client = new Client();
//		client.setAddress("abajpsoidfjapsd");
//		client.setCPF("98909809809");
//		client.setName("Emanuel Victor");
//		client.setRG("RADSFASDFAG");
//		
//		
//		Brand brandFord = new Brand();
//		brandFord.setDescription("FORD");
//		
//		Brand brandFiat = new Brand();
//		brandFiat.setDescription("FIAT");
//		
//		Brand brandBMW = new Brand();
//		brandBMW.setDescription("BMW");
//		
//		Brand brandMercedes = new Brand();
//		brandMercedes.setDescription("MERCEDES");
//		
//		
//		Brand brandAux = new  Brand();
//		brandAux.setDescription("w");
//		
//		Object[] list = daoBrand.find(brandAux);
//		for (int i = 0; i < list.length; i++) {
//			System.out.println(list[i].toString());
//		}
		
	//	Brand aux = new Brand(27);
	//	System.out.println(daoBrand.findByPrimaryKey(aux));
		

		
		
//		daoBrand.save(brandBMW);
//		daoBrand.save(brandMercedes);
//		daoBrand.save(brandFord);
//		daoBrand.save(brandFiat);
//		daoBrand.commit();
//		
//		daoClient.commit();
//		daoBrand.commit();
//		daoModel.commit();
//		daoVehicle.commit();
//		
		
//		brand.setIdBrand(10);
//		brand.setDescription("Fiat");
//		Field listFields[] = Reflection.getFields(c, false);
//		System.out.println("ANTES");
//		for (int i = 0; i < listFields.length; i++) {
//			System.out.println(Reflection.getFieldValue(listFields[i], brand));
//		}
//		
//		
//		Reflection.setFieldValue(listFields[0], brand, new Integer(50));
//		Reflection.setFieldValue(listFields[1], brand, new String("BMW"));
//		System.out.println("DEPOIS");
//		for (int i = 0; i < listFields.length; i++) {
//			System.out.println(Reflection.getFieldValue(listFields[i], brand));
//		}
//		
//		
//		System.out.println("Update : "+Query.getSQLUpdate(brand));
//		System.out.println("Select : "+Query.getSQLSelect(brand));
//		System.out.println("Insert : "+Query.getSQLInsert(brand));
//		System.out.println("Delete : "+Query.getSQLDelete(brand));
		
//		// Cria uma classe Brand.class e irá verificar quais anotations estão nela
//		Class <?> c = Brand.class;
//		if (c.isAnnotationPresent(Entity.class)) {
//			System.out.println("Entity anotada");
//			if (c.isAnnotationPresent(Table.class)) {
//				Table table = c.getAnnotation(Table.class);
//				System.out.println("Nome da tabela: " + table.name());
//				
//				Field fields[] = c.getDeclaredFields();
//				
//				for (int i = 0; i < fields.length; i++) {
//					if (fields[i].isAnnotationPresent(Id.class)) {
//						System.out.println("@Id");
//					}
//					if (fields[i].isAnnotationPresent(GeneratedValue.class)) {
//						System.out.println("@GeneratedValue");
//					}
//					if (fields[i].isAnnotationPresent(Column.class)) {
//						Column column = fields[i].getAnnotation(Column.class);
//						System.out.println("@Column(name = '"+ column.name() + ",type=" + column.type());
//					}
//					System.out.println("Nome: " + fields[i].getName()+"\n");
//				}
//			}
//		}else
//			System.out.println("Entity não anotada");
	}

}
