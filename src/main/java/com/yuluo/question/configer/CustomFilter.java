package com.yuluo.question.configer;

import org.apache.commons.io.IOUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;

/**
 * describe
 *
 * @author: YuLuo
 * @date: 2019/4/5
 * @since: JDK 1.8
 * @version: v1.0
 */

public class CustomFilter  extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		System.out.println("CustomFilter.doFilterInternal --------> servletpath: " + request.getServletPath() + ", method:" + request.getMethod());

		String sign = request.getHeader("sign");
		System.out.println("sign:" + sign );
		CustomRequest customRequest = new CustomRequest(request);
		ServletInputStream inputStream = customRequest.getInputStream();
		String s = IOUtils.toString(inputStream);
		System.out.println("拦截的内容：" + s);

		CustomResponse customResponse = new CustomResponse(httpServletResponse);
		filterChain.doFilter(customRequest, customResponse);
		customResponse.getStringBuilder(Charset.forName("utf-8"));
		customResponse.setHeader("sign" , "response code success !1");
		customResponse.copyBodyToResponse();
	}

	/**
	 * 请求装饰
	 */
	public final class CustomRequest extends HttpServletRequestWrapper{

		private final byte[] body ;

		public CustomRequest(HttpServletRequest request) throws IOException {
			super(request);
			body = IOUtils.toString(this.getReader()).getBytes(Charset.forName("utf-8"));
		}

		@Override
		public BufferedReader getReader() throws IOException {
			return new BufferedReader(new InputStreamReader(this.getRequest().getInputStream()));
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			final ByteArrayInputStream bais = new ByteArrayInputStream(body);
			final ServletInputStream sis = this.getRequest().getInputStream();
			return new ServletInputStream() {
				@Override
				public boolean isFinished() {
					return sis.isFinished();
				}

				@Override
				public boolean isReady() {
					return sis.isReady();
				}

				@Override
				public void setReadListener(ReadListener readListener) {
					sis.setReadListener(readListener);
				}

				@Override
				public int read()  {
					return bais.read();
				}
			};
		}
	}

	/**
	 * 响应装饰
	 */
	public final class CustomResponse extends ContentCachingResponseWrapper {

		public CustomResponse(HttpServletResponse response) {
			super(response);
		}

		void getStringBuilder(Charset charset) {
			byte[] entity = new byte[1024];
			InputStream is = this.getContentInputStream();

			try {
				int len = is.read(entity);
				System.out.println("返回的内容：" + new String(entity, 0, len, charset));
			} catch (IOException var5) {
				var5.printStackTrace();
			}
		}
	}

}
