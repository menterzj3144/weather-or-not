# weather-or-not
Simple mobile weather application that also uses weather information to show the user the best days/times to do certain activities.

Uses the [open weather map API](https://openweathermap.org/api)

[Android Manifest](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/AndroidManifest.xml)

[WeatherOrNotApp](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/java/com/team3/weatherornot/WeatherOrNotApp.kt) - Instantiates APIManager on app startup

[Portrait Layouts](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/res/layout)

[Landscape Layouts](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/res/layout-land)

[Kotlin Activity Files](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/java/com/team3/weatherornot/ui) - Today, Hourly, Weekly activities. Suggest/select fragments

[APIManager](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/java/com/team3/weatherornot/api/APIManager.kt)

[Custom Location Manager](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/java/com/team3/weatherornot/location/MyLocationManager.kt)

[Navigation](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/java/com/team3/weatherornot/navigation) - Code for app navigation

[Data Classes](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/java/com/team3/weatherornot/weather) - For storing weather information

[Database](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/main/java/com/team3/weatherornot/database) - DAO and additional database utils

[JSON Database File](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/assets/db.json)

[Utils](https://github.com/menterzj3144/weather-or-not/blob/main/code/app/src/main/java/com/team3/weatherornot/SuggestSelectUtils.kt) - Functions not related to UI

[Tests](https://github.com/menterzj3144/weather-or-not/tree/main/code/app/src/test/java/com/team3/weatherornot)

## Future
- [ ] Setting to switch temperature units
- [ ] Allow the user to input their own activities and preferred conditions
- [ ] Update weather information after a certain amount of time
