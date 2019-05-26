package com.kunlinr.lifealien.test.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.junit.Test;

public class SerializableTest {
	public void serializable() {
		
		Person person = new Person();
		person.setUserName("goukunlin");
		person.setPassword("123456");
		person.setTitle("坤林");
		person.setNickName("小坤");
		person.setOrg("jiuqi");
		OutputStream outputStream = null;
		ObjectOutputStream objOutputStream = null;
		try {
			outputStream = new FileOutputStream("person.txt");
			objOutputStream = new ObjectOutputStream(outputStream);
			objOutputStream.writeObject(person);
			objOutputStream.close();
			outputStream.close();
		}catch(IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}finally {
		}
	}
	@Test
	public void readSerializable() {
		ObjectInputStream  objOutputStream = null;
		try {
			objOutputStream = new ObjectInputStream (new FileInputStream("person.txt"));
			Person readObject = (Person)objOutputStream.readObject();
			System.out.println(readObject.toString());
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}finally {
		}
	}
}
