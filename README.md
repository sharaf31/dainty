# Meeting Scheduler

## Requirement

Your employer has an existing system for employees to submit booking requests for meetings in the boardroom. Your
employer has now asked you to implement a system for processing batches of booking requests.

Input Your processing system must process input as text. The first line of the input text represents the company office
hours, in 24 hour clock format, and the remainder of the input represents individual booking requests. Each booking
request is in the following format.

``[request submission time, in the format YYYY-MM-DD HH:MM:SS] [employee id]``<br>``
[meeting start time, in the format YYYY-MM-DD HH:MM] [meeting duration in hours]``

A sample text input follows, to be used in your solution.

### Input

```text
0900 1730
2011-03-17 10:17:06 EMP001
2011-03-21 09:00 2
2011-03-16 12:34:56 EMP002
2011-03-21 09:00 2
2011-03-16 09:28:23 EMP003
2011-03-22 14:00 2
2011-03-17 10:17:06 EMP004
2011-03-22 16:00 1
2011-03-15 17:29:12 EMP005
2011-03-21 16:00 3
```

Output Your system must provide a successful booking calendar as output, with bookings being grouped chronologically by
day. For the sample input displayed above, your system must provide the following output.

### Output

```text
2011-03-21
09:00 11:00 EMP002
2011-03-22
14:00 16:00 EMP003
16:00 17:00 EMP004
```

### Constraints

* No part of a meeting may fall outside office hours.
* Meetings may not overlap.
* The booking submission system only allows one submission at a time, so submission times are guaranteed to be unique.
* Bookings must be processed in the chronological order in which they were submitted.
* The ordering of booking submissions in the supplied input is not guaranteed.

### Notes

1. The current requirements make no provision for alerting users of failed bookings; it is up to the user to confirm
   that their booking was successful.
2. Although the system that you produce may open and parse a text file for input, this is not part of the requirements.
   As long as the input text is in the correct format, the method of input is up to the developer.

## Solution

Simple Application to Take input via the commandline and output the meetings which complies to the contstraints

### Technologies used to Develop

* Java 11
* Maven 3.8.3
* Junit 4.13.2
* Lombok 1.18.20
* IntelliJ Idea CE

### System requirements

* JDK v11 and above
* Maven 3.8 and above

Class path should be set for the above language and framework.

### How to Run

1. Import the project into the workspace from github. 

> git clone https://github.com/sharaf31/dainty.git

2. On Terminal root directory of the project, Type below command.

> mvn clean install

3. To try Sample input below command and enter the text from [Input](#input) you can expect results as [Output](#output)
> mvn exec:java -Dexec.mainClass="com.akqa.Main"


### Test Cases

1. All meetings days are successfully created
2. All meetings are successfully created for corresponding days
3. Edge Testcases
    1. None of the meetings are created due to non-business hours
    2. Only single meeting is created rest of the requests are ignored and created meetings times are validated.
