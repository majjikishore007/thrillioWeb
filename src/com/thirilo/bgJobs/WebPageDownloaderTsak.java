//package com.thirilo.bgJobs;
//
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//
//import com.thirilo.dao.BookMarkDao;
//import com.thirilo.utill.HttpConnect;
//import com.thirilo.utill.IOUtil;
//import com.thirlo.entites.Weblink;
//import com.thirlo.entites.Weblink.DownloadStaus;
//
//public class WebPageDownloaderTsak implements Runnable {
//	private static BookMarkDao dao = new BookMarkDao();
//
//	private static final long TIME_FRAME = 3000000000L;
//
//	public WebPageDownloaderTsak(boolean downloadAll) {
//		this.downloadAll = downloadAll;
//	}
//
//	private boolean downloadAll = false;
//
//	ExecutorService downloadExecutor = Executors.newFixedThreadPool(5);
//
//	private static class Downloader<T extends Weblink> implements Callable<T> {
//		private T weblink;
//
//		public Downloader(T weblink) {
//			this.weblink=weblink;
//		}
//
//		@Override
//		public T call()  {
//			try {
//				if (!(weblink.getUrl().endsWith("pdf"))) {
//					weblink.setDownloadStatus(DownloadStaus.FAILED);
//					String htmlPage = HttpConnect.download(weblink.getUrl());
//					weblink.setHtmlPage(htmlPage);
//				} else {
//					weblink.setDownloadStatus(DownloadStaus.NOT_ELIGIBLE);
//				}
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (URISyntaxException e) {
//				e.printStackTrace();
//			}
//
//			return weblink;
//		}
//
//	}
//
//	@Override
//	public void run() {
//		while (!Thread.currentThread().isInterrupted()) {
////			getweblinks
//			List<Weblink> weblinks = getWebLinks();
//
////			download concurently
//			if (weblinks.size() > 0) {
//				download(weblinks);
//			} else {
//				System.out.println("No new  webLink to download!!");
//			}
//
////			wait
//			try {
//				TimeUnit.SECONDS.sleep(15);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//		downloadExecutor.shutdown();
//	}
//
//	private void download(List<Weblink> weblinks) {
//		List<Downloader<Weblink>> tasks = getTasks(weblinks);
//		List<Future<Weblink>> futures = new ArrayList<>();
//
//		try {
//			futures = downloadExecutor.invokeAll(tasks, TIME_FRAME, TimeUnit.NANOSECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		for (Future<Weblink> future : futures) {
//			try {
//				if (!future.isCancelled()) {
//					Weblink weblink = future.get();
//					String webPage = weblink.getHtmlPage();
//					if (webPage != null) {
//						IOUtil.write(webPage, weblink.getId());
//						weblink.setDownloadStatus(DownloadStaus.SUCCESS);
//						System.out.println("Download Success: " + weblink.getUrl());
//					} else {
//						System.out.println("NOT DownloadED: " + weblink.getUrl());
//					}
//				} else {
//					System.out.println("task is canceled" + Thread.currentThread());
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//
//	private List<Downloader<Weblink>> getTasks(List<Weblink> weblinks) {
//		List<Downloader<Weblink>> tasks = new ArrayList<>();
//		for (Weblink weblink : weblinks) {
//			tasks.add(new Downloader<Weblink>(weblink));
//		}
//		return tasks;
//	}
//
//	private List<Weblink> getWebLinks() {
//		List<Weblink> weblinks =new ArrayList<Weblink>();
//
//		if (downloadAll) {
//			weblinks = dao.getAllWeblinks();
//			downloadAll = false;
//		} else {
//			weblinks = dao.getWeblinks(Weblink.DownloadStaus.NOT_ATTEMPTED);
//		}
//
//		return weblinks;
//	}
//
//}
