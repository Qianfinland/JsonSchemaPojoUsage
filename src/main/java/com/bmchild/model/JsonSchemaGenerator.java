package com.bmchild.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

public class JsonSchemaGenerator {

	public static void main(String[] args) throws IOException {
		BufferedReader stdin = new BufferedReader ( new InputStreamReader( System.in ) );
		String input;
		System.out.println("Write schema for creating schema and write pojo to create pojo:");
		input =stdin.readLine();
		System.out.println("The input value is: " + input);
		if(input.equals( "schema"))
        {
			FromPojoToSchema() ;
        }
		else if(input.equals("pojo"))
        {
			FromSchemaToPojo();
        }
		else 
		{
			System.out.println("Wong input! You type other thing else!");
		}

	}
	
	static void FromPojoToSchema() throws FileNotFoundException, JsonProcessingException 
	{
		ObjectMapper mapper = new ObjectMapper();
      SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
      mapper.acceptJsonFormatVisitor(SimplePojo.class, visitor);
      JsonSchema schema = visitor.finalSchema();
      //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
      System.out.println("Schema text file created successfully!");
      PrintWriter writer = new PrintWriter("schema.txt");
      writer.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema));
      writer.close();
	}
	
	static void FromSchemaToPojo() 
	{
		System.out.println("Create the pojo file!");
	}
	

}
