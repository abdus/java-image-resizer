package net.abdus.resizeimage.RequestResponseEntity;

public class Resize {

	public static class RequestBody {
		public String base64;
		public float scale;
		public String url;

	}

	public static class ResponseBody {
		public String err;
		public String imageBase64;

		public ResponseBody(String err, String imageInBase64) {
			this.err = err;
			this.imageBase64 = imageInBase64;
		}
	}
}
