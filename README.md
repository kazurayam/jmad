

# [Katalon Studio] Alternative Test Suite Execution which shows failed tests only

- https://github.com/kazurayam/jmad

This is a small Katalon Studio project for demonstration purpose.
You can download the zip of this project from the [Releases page](), unzip it locally to open in your Katalon Studio.


## Background

This project was created to propose a solution to a post raised in the Katalon User forum at:

- https://forum.katalon.com/t/report-only-failed-tests-no-passed-tests-please/84556

The Original Poster (PO) had a very large Testsuite which contains over 2000 Testcases. When he run the Test Suite, it takes 8 hours, and just a few of Test cases fails. He want to find which Testcases failed and fix the failing test cases. The built-in HTML Report becomes inevitablly very long. The report shows the information of ALL of the testcases, regardless PASSED or FAILED. The PO spent hard time looking for the failed testcases amongst the mass of 2000. He got tired clicking the left-button of the Mouse for hundreds of times. He asked in the forum:

> Is it possible to make the HTML Report of Test Suite execution to show Failed test cases only?

However, the HTML Report of Katalon Studio is not customizable at all.


## Second best proposal

The following URL I know, the following URL shows us the source code of the program modules that compiles the Report HTML.

github.com
katalon-studio/katalon-studio-testing-framework/blob/master/Include/scripts/groovy/com/kms/katalon/core/reporting/ReportUtil.java

I believe, Katalon Studio calls `com.kms.katalon.core.reporting.ReportUtil#writeLogRecordToFiles()` to compile the reports when a Test Suite finished.

I can not change the Katalon Studio itself, but I would be able to "reuse" the open-source codes to produce a second best solution.

I created a GitHub project "ReportFailedTestOnly":

- https://github.com/kazurayam/ReportFailedTestsOnly

There I developed a set of Groovy classes, and created a jar, which is published at

- https://github.com/kazurayam/ReportFailedTestsOnly/releases/tag/0.1.1

Using this jar, any Katalon Studio users can let their projects to produce a "HTML Test Suite report for Failed tests only".

## How to use the solution

You create a Katalon Studio project with a Test Suite.

You want to download the [`ReportFailedTestsOnly-0.1.1.jar`]() and located it in the `Drivers` folder.

You want to close the project and reopen it to let Katalon Studio recognize the downloaded jar.

You want to create a Test Case like this:

- https://github.com/kazurayam/jmad/blob/main/Scripts/main/AltTestSuiteReport/Script1677323810828.groovy

Here I named it as `AltTestSuiteReport`, but you can name it any.

The Test Case contains the name of Test Suite in the code, like

```
Path reportDir = rdu.getLatestTestSuiteReportDirOf("TS1")
```

The example has a Test Suite named as `"TS1"`. You MUST change this string constant to be your own Test Suite name.

Now you are ready.

You want to run your Test Suite as you have done many times.

Once your Test Suite finished, you want to run the new Test Case `AltTestSuiteReport`. It wil run fast enough. It will take tens of seconds, but will not take any hours.

The test case will create a new file at

- `<projectDir>/AltTestSuiteReport/AltTestSuiteReport.html`

You want to open this HTML with any web browser.

You will find the page looks similar to the built-in report, but slightly different. It has a button on the top.

It starts with a page where both of PASSED tests and FAILED tests are displayed:

![PASSED and FAILED](https://kazurayam.github.io/jmad/images/Report_of_PASSED_and_FAILED.png)

By clicking the button, you can change the page to show Failed tests only.

![FAILED only](https://kazurayam.github.io/jmad/images/Report_of_FAILED_only.png)

By clicking the button again, the PASSED test cases will come up.

## Movie

Here is a video how I operated this project:

[![jmad](https://img.youtube.com/vi/UQFoIpGyqVA/0.jpg)](https://www.youtube.com/watch?v=UQFoIpGyqVA)

## Conclusion

Katalon Studio offers [Data driven testing](https://docs.katalon.com/docs/author/data-driven-testing/data-driven-testing-with-katalon-studio). I suppose this featured is liked by users a lot. Once accustomed, users tend to create large data sets to drive their tests. For example, the [OP](https://forum.katalon.com/t/report-only-failed-tests-no-passed-tests-please/84556/5) created 2300 lines of data. I suppose he/she is not exceptional. Rather he/she is a typical user of "Data driven testing".

The HTML Report that contains 2000 test cases is too large. The report is so large that it becomes useless. Anyone feels like to see only a few of FAILED tests amongst 2000. They do not like to wander in the mass. The report without filtering capability makes us frustrated.

However, I guess that *Katalon team would never address this issue* because I know in the last few years they have been busy for developing their TestOps product. They seem to find that improving the basic HTML report doesn't help profitting. I was worried that those Katalon users who like the "Data driven testing" would be left unsatisfied for more years to come.

Therefore I have worked out for this 2nd-best solution ["ReportFailedTestsOnly"](https://github.com/kazurayam/ReportFailedTestsOnly/releases). Please try it.





