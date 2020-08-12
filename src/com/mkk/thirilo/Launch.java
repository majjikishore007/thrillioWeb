package com.mkk.thirilo;

import java.util.List;

import com.thirilo.bgJobs.WebPageDownloaderTsak;
import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;
import com.thirlo.managers.BookMarkMannager;
import com.thirlo.managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<BookMark>> bookmarks;

	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		users = UserManager.getInstance().getUsers();
		bookmarks = BookMarkMannager.getInstance().getBookmarks();

//		System.out.println("Printing data ...");
//		printUserData();
//		printBookmarkData();
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}

	}

	private static void printBookmarkData() {
		for (List<BookMark> bookmarkList : bookmarks) {
			for (BookMark bookmark : bookmarkList) {
				System.out.println(bookmark);

			}
		}

	}

	private static void start() {
		// TODO Auto-generated method stub
		// System.out.println("\n2. start ..");
		for (User user : users) {

			View.browse(user, bookmarks);
		}

	}

	public static void main(String[] args) {
		loadData();
		start();
//		runDownloaderJob();
	}

	private static void runDownloaderJob() {
		WebPageDownloaderTsak task = new WebPageDownloaderTsak(true);
		Thread thread = new Thread(task);
		thread.start();
	}

}
