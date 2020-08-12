package com.mkk.thirilo;

import java.util.List;

import com.thirilo.controllers.BookmarkController;
import com.thirilo.partner.Shareable;
import com.thirlilo.constants.KidFriendlyStatus;
import com.thirlilo.constants.UserType;
import com.thirlo.entites.BookMark;
import com.thirlo.entites.User;

public class View {

	public static void browse(User user, List<List<BookMark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + "is browisingitems.....");

		for (List<BookMark> bookmarkList : bookmarks) {
			for (BookMark bookmark : bookmarkList) {

				boolean isBookmarked = getBookmarkDecision(bookmark);
				if (isBookmarked) {

					BookmarkController.getInstance().saveUserBookmark(user, bookmark);
					System.out.println("New item bookmarked .." + bookmark  );
				}

				if (user.getUserType().equals(UserType.CHIEF_EDITOR) || user.getUserType().equals(UserType.EDITOR)) {
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidfriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidfriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidfriendlyStatus(user, kidFriendlyStatus, bookmark);

						}
					}
					if (bookmark.isKidFriendlyEligible() && bookmark instanceof Shareable) {
						boolean isShared = getSharedDecesion();
						if (isShared) {
							BookmarkController.getInstance().share(bookmark, user);
						}
					}
				}
			}
		}

	}

	private static boolean getSharedDecesion() {
		// TODO Auto-generated method stub
		return Math.random() < 0.5 ? true : false;
	}

	private static KidFriendlyStatus getKidfriendlyStatusDecision(BookMark bookmark) {
		return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;

	}

	private static boolean getBookmarkDecision(BookMark bookmark) {

		return Math.random() < 0.5 ? true : false;
	}

}
