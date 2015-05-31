package activityPlanner.rating

class ActivityRatingDelegate {
	private List<ActivityRating> activityRatings = []
	
	ActivityRatingDelegate(List<ActivityRating> activityRatings) {
		this.activityRatings = activityRatings
	}
	
	void activity(String name, Closure cl) {
		ActivityRating rating = ActivityRating.make(cl)
		rating.activityName = name
		activityRatings.add(rating)
	}
}
