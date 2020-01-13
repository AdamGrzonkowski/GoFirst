import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Program {
	
	private static Map<String, Long> filesToShow = new TreeMap<String, Long>();
	
	public static void main(String[] args){

		File fileOrDirectory = new File ("D:\\Documents\\Informatyka - studia\\S2");
		
		CheckFiles(fileOrDirectory);
	
		filesToShow.forEach((filePath, size) -> {
			System.out.println(filePath + " " + size + " MB");
		});
	}
	
	public static void CheckFiles(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory()) {
			for (File file : fileOrDirectory.listFiles()) {
			      if (file.isFile()) {
			    	  ProcessFile(file);
			      } else if (file.isDirectory()) {
			    	  CheckFiles(file);
			      }
			}
		}
		else {
			ProcessFile(fileOrDirectory);
		}
	}
	
	private static void ProcessFile(File file) {
		long fileSize = GetFileSize(file.length());
		
		if (CompareDates(file.lastModified()) && fileSize > 10){
			filesToShow.put(file.getAbsolutePath(), fileSize);
		}
	}
	
	public static boolean CompareDates(long fileModificationTime) {
		Date input = new Date(fileModificationTime);
		LocalDate date = input.toLocalDate();
		LocalDate thirtyDaysAgo = LocalDate.now().plusDays(-30);
		
		return date.isBefore(thirtyDaysAgo);
	}
	
	public static long GetFileSize(long size) {
		return size / 1024 / 1024; // converts size from bytes to megabytes and checks if it satisfies business rule
	}
}