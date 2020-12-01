package hzzwebcommon.step;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hzzbatchcommon.utils.Tools;
import hzzbatchcommon.step.HZZBatchStep;

@RestController
@RequestMapping(value = "/HZZBatchApi/Step")
public class StepController {
	public static void main(String[] args) {
		getEndTime("ジョブID");
	}
	@RequestMapping(value = "/GetEndTime", method = RequestMethod.GET)
	public static String getEndTime(@RequestParam("jobId") String jobId) {
		String result = null;
		FileOutputStream outputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		try {
			// String filePath = Tools.getValue("acceptFilePath");
			// filePath = filePath + jobId + "_accept.xml";
			String filePath = null;
			filePath = "C:\\temp\\ジョブID_time.xml";
			File file = new File(filePath);
			if (file.exists()) {
				SAXReader reader = new SAXReader();
				Document doc = reader.read(file);
				Element retCode = (Element) doc.selectObject("/JOB/STEP/");
				retCode.getQName("TIME");
				result = retCode.getStringValue();
			} else {
				file.createNewFile();

				try {
					outputStream = new FileOutputStream(filePath);
					// エンコード
					// EUC-JPやUTF-8など
					outputStreamWriter = new OutputStreamWriter(outputStream, "SJIS");

					outputStreamWriter.close();

				} catch (UnsupportedEncodingException e) {
					System.out.print(e);
				} catch (IOException e) {
					System.out.print(e);
				} finally {
					if (outputStreamWriter != null) {
						outputStreamWriter.close();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
}

	@RequestMapping(value = "/GetInitProgress}", method = RequestMethod.GET)
	public String getInitProgress(@RequestParam("jobId") String jobId) {

		String filePath = Tools.getValue("stepFilePath");
		filePath = filePath + jobId + "_step.xml";
		// filePath = "C:\\temp\\ジョブID_accept.xml";
		File file = new File(filePath);
		if (file.exists()) {
			HZZBatchStep hzzBatchStep = new HZZBatchStep();
			try {
				hzzBatchStep.initStep(jobId);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			try {
				
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return filePath;
		
		
	}

	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/GetProcess", method = RequestMethod.GET)
	public String getProcess(@RequestParam("jobId") String jobId, @RequestParam("step") String step,@RequestParam("name")String name) {
		String status = null;
		String filePath = Tools.getValue("stepFilePath");
		filePath = filePath + jobId + "_step.xml";
		// filePath = "C:\\temp\\ジョブID_accept.xml";
		File file = new File(filePath);
		if (file.exists()) {
			HZZBatchStep hzzBatchStep = new HZZBatchStep();
			hzzBatchStep.setStep(jobId, step, name);
		}
		if (status == "0") {
			return "未実行";
		} else if (status == "1") {
			return "実行中";
		} else if (status == "2") {
			return "完了";
		} else {
			return "エラー";
		}
	}
	
	

}
