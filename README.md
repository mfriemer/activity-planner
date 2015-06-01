# activity-planner

API to determine which activities would be good for the given dates.

Url can contain a ZIP/Postal code, State/City or Latitude/Longitude:

activity-planner/location/55401/activityPlans
activity-planner/location/MN/Minneapolis/activityPlans
activity-planner/location/44.98/-93.27/activityPlans

Optional date or startDate & endDate parameters.  Can be formatted as "yyyy-MM-ddThh:mm:ss" or as milliseconds since Unix epoch.  A Maximum of 10 days at a time can be requested.  Default is today's date:

activity-planner/location/55401/activityPlans?date=2015-06-01T00:00:00
activity-planner/location/55401/activityPlans?date=1433116800000
activity-planner/location/55401/activityPlans?startDate=2015-06-01T00:00:00&endDate=2015-06-04T00:00:00

Data returned is an "activity plan" for the dates requested, consisting of the date and a list of activities that would be good for that date.  Activities are provided as property ratings.defPath which points to a groovy DSL file with the format:

activities {
	activity "skiing", {
		tempRange 0, 32
	}
	activity "swimming", {
		tempRange 75, 95
		excludeConditions "rain", "snow"
	}
	activity "golf", {
		tempRange 50, 75
		windSpeedRange 0, 15
		excludeConditions "rain", "snow"
	}
}
