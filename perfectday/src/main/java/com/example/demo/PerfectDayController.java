package com.example.demo;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;





	

	
	
@RestController
@CrossOrigin(origins="*")
public class PerfectDayController {
@Autowired
PerfectDayRepo perfectdayRepo;
		
		@GetMapping("/perfectday/find")
		public  PerfectDay findById(@RequestParam int id) {
			
			
			PerfectDay perfectday =perfectdayRepo.findById(id).get();
			 
			 perfectday.setImage(decompressBytes(perfectday.getImage()));
			 
			 return perfectday;
			
		}
		
		@PostMapping("/perfectday/add")
		public String addProduct(@RequestParam ("dietFile") MultipartFile myFile,
				String name,
				String dob,
				String qual,
				String height,
				String caste,
				String  phoneno) {
			
			try {
				PerfectDay perImage = new PerfectDay(name,dob,qual,height,caste,phoneno,
						compressBytes(myFile.getBytes()));
				perfectdayRepo.save(perImage);		
			}catch(Exception e) {
				
			}
			
			
			
			return "Successfully Added New Product";
			
		}
		
		@GetMapping("/perfectday/delete")
		public List<PerfectDay> deletePerfectDay(@RequestParam int id){
			
			perfectdayRepo.deleteById(id);
			
			return getAllProducts();
		}
		@GetMapping("/perfectday/all")
		public List<PerfectDay> getAllProducts(){
			
			List<PerfectDay> drList = new ArrayList<PerfectDay>();
			
			List<PerfectDay> resDrList = perfectdayRepo.findAll();
			PerfectDay perfectday = null;
			for(int i=0;i<resDrList.size();i++) {
				
				perfectday = resDrList.get(i);
				
				perfectday.setImage(decompressBytes(perfectday.getImage()));
				
				drList.add(perfectday);
				
			}
			
			
			return drList;
		}
		
		// compress the image bytes before storing it in the database
				public static byte[] compressBytes(byte[] data) {
					Deflater deflater = new Deflater();
					deflater.setInput(data);
					deflater.finish();

					ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
					byte[] buffer = new byte[1024];
					while (!deflater.finished()) {
						int count = deflater.deflate(buffer);
						outputStream.write(buffer, 0, count);
					}
					try {
						outputStream.close();
					} catch (IOException e) {
					}
					System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

					return outputStream.toByteArray();
				}

				// uncompress the image bytes before returning it to the angular application
				public static byte[] decompressBytes(byte[] data) {
					Inflater inflater = new Inflater();
					inflater.setInput(data);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
					byte[] buffer = new byte[1024];
					try {
						while (!inflater.finished()) {
							int count = inflater.inflate(buffer);
							outputStream.write(buffer, 0, count);
						}
						outputStream.close();
					} catch (IOException ioe) {
					} catch (DataFormatException e) {
					}
					return outputStream.toByteArray();
				}


}

