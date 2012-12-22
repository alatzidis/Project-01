package eu.quietroom.emp.entelligence.birt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.logging.Level;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineConstants;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import eu.quietroom.emp.entelligence.EMPSettings;

public class BirtIntegration {

	static IReportEngine engine = null;
	public static final int EMP_DAILY_POWER = 1;
	public static final int EMP_WEEKLY_POWER = 2;
	public static final int EMP_WEEKLY_EMMISSIONS = 3;
	public static final int SMP_IMAGES = 4;
	
	static {
		final EngineConfig config = new EngineConfig();
		config.setEngineHome(EMPSettings.birt_home);
		config.setResourcePath("reports/images");
		try {
			// config.setLogConfig(c:/temp, Level.FINE);

			Platform.startup(config); // If using RE API in Eclipse/RCP
										// application this is not needed.
			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			engine = factory.createReportEngine(config);
			engine.changeLogLevel(Level.SEVERE);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	
	private void runTasks(int reportType, String date) throws EngineException, IOException {
		File reportDesign = null;
		String outFile = EMPSettings.path_to_report_private_folder + "\\";
		StringBuffer feederContent = new StringBuffer();
		feederContent.append("title;file;date;frequency;type\n");
		switch (reportType) {
		case BirtIntegration.SMP_IMAGES:
			String date1 = date.split("#")[0];
			String date2 = date.split("#")[1];
			reportDesign = new File("reports/smp-previous.rptdesign");
			outFile += "images/image" + date2 + ".html"; 
			FileInputStream fis = new FileInputStream(reportDesign);
			IReportRunnable design = engine.openReportDesign(fis); 
			IRunAndRenderTask task = engine.createRunAndRenderTask(design); 
			task.setLocale(new Locale("en-US"));
			task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, BirtIntegration.class.getClassLoader()); 
			task.setParameterValue("dateToForecast", (date1));
			task.setParameterValue("dateToForecast2", (date2));
			task.validateParameters();
			HTMLRenderOption options = new HTMLRenderOption();		
			options.setOutputFileName(outFile);
			options.setOutputFormat("html");
			options.setEmbeddable(true);
			task.setRenderOption(options);
			task.run();
			task.close();
			System.out.println("Wait a second !");
			try {Thread.sleep(1000);}catch(Exception e) {}
			Document doc = Jsoup.parse(new File(outFile), "UTF-8", "");
			String imagePath = doc.select("#__bookmark_1").attr("src");
			File afile = new File(imagePath.replace("file:/", ""));
			System.out.println("temp image path is : "  + afile);
			String newPath = EMPSettings.smp_photos+ "/smp_" + date2 + ".png";
			File bfile = new File(newPath);
			System.out.println("moving temp image to : " + newPath);
			InputStream inStream = null;
			OutputStream outStream = null;
    	    System.out.println("opening file to read...");
			inStream = new FileInputStream(afile);
			System.out.println("opening file to write ...");
    	    outStream = new FileOutputStream(bfile); 
    	    byte[] buffer = new byte[1024];
    	    int length;
    	    System.out.print("copying file contents...");
    	    while ((length = inStream.read(buffer)) > 0){
    	    	System.out.print(".");
    	    	outStream.write(buffer, 0, length);
    	    }
    	    System.out.println("\\nFinished !");
    	    inStream.close();
    	    outStream.close();	 
			return;
		case BirtIntegration.EMP_DAILY_POWER:
			reportDesign = new File("reports/emp_daily.rptdesign");
			outFile += EMPSettings.energy_path + "\\" + EMPSettings.daily_path + "\\" + EMPSettings.report_prefix + "-" + EMPSettings.power_prefix + "-" +	EMPSettings.daily_prefix + "_" + date + ".pdf";
			feederContent.append("energy_daily_" + date + ";sites/all/files/private/energy/daily/" + EMPSettings.report_prefix + "-" + EMPSettings.power_prefix + "-" +	EMPSettings.daily_prefix + "_" + date + ".pdf" + ";" + date + ";Daily;Energy");
			try {
				runTasks(BirtIntegration.SMP_IMAGES, date + "#7");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#15");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#30");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#60");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#90");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#180");
				runTasks(BirtIntegration.SMP_IMAGES, date + "#360");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case BirtIntegration.EMP_WEEKLY_POWER:
			reportDesign = new File("reports/emp_weekly.rptdesign");			
			outFile += EMPSettings.energy_path + "\\" + EMPSettings.weekly_path + "\\" + EMPSettings.report_prefix + "-" + EMPSettings.power_prefix + "-" +	EMPSettings.weekly_prefix + "_" + date + ".pdf";
			feederContent.append("energy_weekly_" + date + ";sites/all/files/private/energy/weekly/" + EMPSettings.report_prefix + "-" + EMPSettings.power_prefix + "-" +	EMPSettings.daily_prefix + "_" + date + ".pdf" + ";" + date + ";Weekly;Energy");
			break;
		case BirtIntegration.EMP_WEEKLY_EMMISSIONS:
			reportDesign = new File("reports/emp_emmissions.rptdesign");
			outFile += EMPSettings.emissions_path + "\\" + EMPSettings.weekly_path + "\\" + EMPSettings.report_prefix + "-" + EMPSettings.emmissions_prefix + "-" +	EMPSettings.weekly_prefix + "_" + date + ".pdf";
			feederContent.append("emissions_weekly_" + date + ";sites/all/files/private/emissions/weekly/" + EMPSettings.report_prefix + "-" + EMPSettings.power_prefix + "-" +	EMPSettings.daily_prefix + "_" + date + ".pdf" + ";" + date + ";Weekly;Emission");
			break;
		default:
			break;
		}		
		
		FileOutputStream fos = new FileOutputStream(new File(EMPSettings.feeder_file_path));
		fos.write(feederContent.toString().getBytes());
		fos.close();
		
		//Open the report design
		FileInputStream fis = new FileInputStream(reportDesign);
		IReportRunnable design = engine.openReportDesign(fis); 
		//Create task to run and render the report,
		IRunAndRenderTask task = engine.createRunAndRenderTask(design); 
		task.setLocale(new Locale("en-US"));
		//Set parent classloader for engine
		task.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, BirtIntegration.class.getClassLoader()); 
					
		//Set parameter values and validate
		task.setParameterValue("dateToForecast", (date));
		task.validateParameters();
						
		//Setup rendering to HTML
		PDFRenderOption options = new PDFRenderOption();
		options.setOutputFileName(outFile);
		options.setOutputFormat("pdf");
				
		task.setRenderOption(options);
		//run and render report
		task.run();
		task.close();
		
		//Call cron of the drupal site to manage the feed
		try {
		    InputStream resp = new URL(EMPSettings.cron).openStream();
		} 
		catch (MalformedURLException e) { 
		    
		} 
		catch (IOException e) {   
		 
		}
	}

	private void shutdown() {
		engine.destroy();
		Platform.shutdown();
	}
	
	public static void createReport(int reportType, String date) {
		BirtIntegration bi = new BirtIntegration();
		try {
			bi.runTasks(reportType, date);
			//bi.shutdown();
		} catch (EngineException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
