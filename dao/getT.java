package com.jsu.wwt.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class getT {
	public String getUser() {
		String a=null,b = null,c=null;
	BufferedReader fr = null;
	try {
		fr = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\io.txt"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String line=null;
	int i=0;
	try {
		while((line=fr.readLine())!=null){
			
			if(i==0) {
				 a=line;
				i++;
		}else if(i==1) {
			 b=line;
			i++;
		}else {
			 c=line;
		}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return a;
	}
	
	
}
