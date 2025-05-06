# openweather-API-Automation

---

## Global Variables (Profiles)
These variables are stored in the `Profiles > default` file:

| Variable     | Value               |
|--------------|---------------------|
| `baseUrl`     | `https://api.openweathermap.org` |
| `apiKey`     | Your OpenWeatherMap API Key |
| `lat`        | `-6.261493` (Jakarta Selatan) |
| `lon`        | `106.810600` (Jakarta Selatan) |

---

## Test cases

###  TC01_Get5DayForecast
- **Endpoint**: `/data/2.5/forecast`
- **Assertions**:
  - Status code = 200
  - Response has `code`,`city`, `message`, `cnt`, and `list`
  - JSON schema validation
  - Forecast weather in 5 days including Temperature, clouds, wind, weather description exist, etc
  
  ###  TC02_GetCurrentAirPollution
- **Endpoint**: `/data/2.5/air_pollution`
- **Assertions**:
  - Status code = 200
  - Response has `coord`, `list of components`, `list of main` and `list of dt`
  - All components are numbers
  - Current Air Polution in specific location
  
  ---

##  How to Run

1. Open the project in **Katalon Studio**
2. Go to **Test Suites > TS_Get5DaysForecast or TS_GetCurrentAirPolution
3. Click **Run** (choose desired environment)
4. Reports will be generated in `Reports/` directory

---

##  Notes

- Use your own **API key** and stay within free-tier rate limits.
- Variables are externalized to the Global Profile.

---

## How to Generate Report

1. Run a **Test Suite**
2. Go to **Reports**
3. Choose the latest report
4. Right-click > **Open with > Report**
