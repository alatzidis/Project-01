package eu.quietroom.emp.entelligence;

import java.util.ArrayList;

import eu.quietroom.emp.utils.downloadUtils.DownloadUtils;


public class EMPDownloader{
	private ArrayList<DownloadData> downloadList;
	
	public EMPDownloader(ArrayList<DownloadData> downloadList){
		this.downloadList = downloadList;
	}
	
	public void downloadFiles(){
		for(DownloadData dd : downloadList){
			if(!dd.getIsCompleted()){
				if(DownloadUtils.downloadFile(dd.getURL(), dd.getSavePath())){
					dd.setIsCompleted(true);
				}
			}
		}
	}
	
	public boolean isCompleted(){
		for(DownloadData dd : downloadList){
			if(!dd.getIsCompleted()){
				return false;
			}
		}
		return true;
	}
	
//	public ArrayList
}
