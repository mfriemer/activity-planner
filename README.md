# activity-planner

API to determine which activities would be good for the given dates.

Url can contain a ZIP/Postal code, State/City or Latitude/Longitude:

activity-planner/location/55401/activityPlans
activity-planner/location/MN/Minneapolis/activityPlans
activity-planner/location/44.98/-93.27/activityPlans

Optional date or startDate & endDate parameters.  Default is today's date

activity-planner/location/55401/activityPlans?date=2015-06-01T00:00:00
activity-planner/location/55401/activityPlans?startDate=2015-06-01T00:00:00&endDate=2015-06-04T00:00:00
