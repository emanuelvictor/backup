package br.edu.udc.sistemas.ia6.emanuelvictor.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Column;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Entity;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.GeneratedValue;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Id;
import br.edu.udc.sistemas.ia6.emanuelvictor.annotation.Table;


public class Tests {
	public static Object execute(Object objectOther) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		// Catch the class of the object and instance 
		// Instancia um objeto a partir da classe do objectOther
		Object obj = objectOther.getClass().newInstance();
		// Get the attributes
		Field fields[] = obj.getClass().getDeclaredFields();
		// Get the methods
		Method methods[] = obj.getClass().getDeclaredMethods();
		
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < methods.length; j++) {
				if (methods[j].getName().toUpperCase().equals("SET" + fields[i].getName().toUpperCase())) {
					for (int j2 = 0; j2 < methods.length; j2++) {
						if (methods[j2].getName().toUpperCase().equals(("GET" + fields[i].getName().toUpperCase()))) {
							methods[j].invoke(obj, methods[j2].invoke(objectOther, new Object[0]));
						}
					}
				}
			}
		}
		return obj;
	}

	
	// Esta função imprime todas as anotações
	public static void printAnnotations(Object objectOther) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			// Verificando se há anotação
			if (objectOther.getClass().isAnnotationPresent(Entity.class)) {
				if (objectOther.getClass().isAnnotationPresent(Table.class)) {
					// Pegando a anotação
						Table table = objectOther.getClass().getAnnotation(Table.class);
						// Como a anotação tem um nomem, que é o nome da tabela do banco. A mesma esta sendo impressa
						System.out.println("Nome da tabela: " + table.name());
					}
				Field fields[] = objectOther.getClass().getDeclaredFields();
				// Percorrendo os campos
				for (int i = 0; i < fields.length; i++) {
					// Verificando se tem a anotação ID e imprimindo
					if (fields[i].isAnnotationPresent(Id.class)) {
						System.out.println("@Id");
					}
					// Verificando se tem a anotação GeneratedValue e imprimindo
					if (fields[i].isAnnotationPresent(GeneratedValue.class)) {
						System.out.println("@GeneratedValue");
					}
					// Verificando se tem a anotação column e imprimindo o tipo
					if (fields[i].isAnnotationPresent(Column.class)) {
						Column column = fields[i].getAnnotation(Column.class);
						System.out.println("@Column(name = '"+ column.name() + ",type=" + column.type());
					}
					System.out.println("Nome: " + fields[i].getName());
				}
			}
		}

	//Esta função imprime todos os métodos
	public static void printMethods(Object objectOther) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
			System.out.println("Todos os métodos de " + objectOther);
			Method[] methods = objectOther.getClass().getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				System.out.println(methods[i].getName());
			}
		}
	}
