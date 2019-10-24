package Watcher;

import java.io.File;
import java.util.ArrayList;


public class DirectoryScanner {
	
    private String watchDirectory;
	private ArrayList<String> currentlyInQueue = new ArrayList<String>();
	private Boolean canScan = true;
    
    
	public DirectoryScanner(String watchDirectory) {
		this.watchDirectory = watchDirectory;
	}
	
	public String getWatchDirectory() {
		return watchDirectory;
	}
	public void setWatchDirectory(String watchDirectory) {
		this.watchDirectory = watchDirectory;
	}

    public ArrayList<String> returnQueue(){
    	if(canScan) {
    		canScan = false;
            return currentlyInQueue;
    	}
    	else {
    		return null;
    	}
    }
	
	public void scanDirectory() {
		File folder = new File(watchDirectory);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> truthList = new ArrayList<String>();
		
		
		for (int i = 0; i < listOfFiles.length; i++) {
			truthList.add(listOfFiles[i].getName());
			
			if(listOfFiles[i].isFile()) {
				if(!currentlyInQueue.contains(listOfFiles[i].getName())) {
				    currentlyInQueue.add(listOfFiles[i].getName());
				}
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
			
		}
		
		for (int i = 0; i < currentlyInQueue.size(); i++) {
			if(!truthList.contains(currentlyInQueue.get(i))) {
				currentlyInQueue.remove(i);
			}
		}
		
		System.out.println("Files currently In Queue for Processing");
		System.out.println(currentlyInQueue.toString());
		canScan = true;
		
	}
	
	

}
