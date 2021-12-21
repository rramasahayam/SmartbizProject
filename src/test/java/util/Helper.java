package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Helper {
		public static String getPropertyValue(String PropertyName) {
		        Properties prop = new Properties();
		        InputStream input = null;
		        String PropertyValue = "";
		        try {            
		            String envFileFullPath = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator + "Rahul"+ File.separator + "Smartbiz" +
		            File.separator + "env"+  File.separator+"default"+ File.separator+"user.properties";
		            File configFile = new File(envFileFullPath);
		            input = new FileInputStream(configFile.getAbsolutePath());
		             prop.load(input);
		             PropertyValue = prop.getProperty(PropertyName);
		             
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        } finally {
		            if (input != null) {
		                try {
		                    input.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		        return PropertyValue;
		    }
		public static void setPropertyValue(String PropertyName, String PropertyValue) {
			Properties prop = new Properties();
			InputStream input = null;
			OutputStream output = null;
			try {            
				String userPropertyFilePath = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator + "Rahul"+ File.separator + "Smartbiz" +
						File.separator + "env"+  File.separator+"default"+ File.separator+"user.properties";
				File configFile = new File(userPropertyFilePath);
				input = new FileInputStream(configFile.getAbsolutePath());
				prop.load(input);
				input.close();
				output = new FileOutputStream(configFile.getAbsolutePath());
				prop.setProperty(PropertyName, PropertyValue);
				prop.store(output, null);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		public static void main(String[] args) {
			System.out.println(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"resources"+File.separator+"VoidedCheck.PNG");
		}
}
