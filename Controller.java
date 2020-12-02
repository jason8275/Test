


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
		
}

	return 

}
