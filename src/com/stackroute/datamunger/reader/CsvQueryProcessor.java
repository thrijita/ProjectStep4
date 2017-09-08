package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	//declaring the variable
	String file;

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
	
		//getting the file name
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		this.file = fileName;
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		// TODO Auto-generated method stub
		//getting the header alone
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		Header header = new Header();
		header.setHeaders(bufferedReader.readLine().split(","));
		return header;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		// TODO Auto-generated method stub
		
		// checking for Integer
		
		// checking for floating point numbers
				
		// checking for date format dd/mm/yyyy
		
		// checking for date format mm/dd/yyyy
		
		// checking for date format dd-mon-yy
		
		// checking for date format dd-mon-yyyy
		
		// checking for date format dd-month-yy
		
		// checking for date format dd-month-yyyy
		
		// checking for date format yyyy-mm-dd
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		bufferedReader.readLine();
		Integer integertype;
		String stringType;
		//splitting the comma and getting the header count
		String[] tempSplit = bufferedReader.readLine().split(",");
		int headerCount = this.getHeader().getHeaders().length;
		DataTypeDefinitions dataTypeDefinitions = new DataTypeDefinitions();
		String[] dataType = new String[headerCount];
		for (int index = 0; index < headerCount; index++) {
			try{
				if(tempSplit[index].contains("/")||tempSplit[index].contains("-"))
				{
				dataType[index]=Date.class.getName();
				}
				
				else
				{
					try
					{
					integertype=Integer.parseInt(tempSplit[index]);
					dataType[index]=integertype.getClass().getName();	
					}
					catch(Exception e)
					{
						dataType[index]=String.class.getName();
					}
				}
			}
			catch(Exception e)
			{
				dataType[index]=Object.class.getName();
			}
		}
		dataTypeDefinitions.setDataTypes(dataType);
		return dataTypeDefinitions;
	}
	}
	
	

	
	


