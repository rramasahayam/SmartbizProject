SBAApplication
=====================

This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Borrower submit a loan application to smartbiz
---------------------------
tags: Borrower, SBA Application Process, Smartbiz, positive

Login Page story/requirements:
1. Borrower should be able to submit a SBA loan application

* Navigate to Smartbiz website
* Verify the landing page has header "Smartbiz"
* Verify "Continue to pre-qualify" button is "disabled"
* Select "Build my business or refinance an existing debt" and select "SBA Loan" option
* Enter "Rahul" in "First Name" textbox
* Enter "Reddy" in "Last Name" textbox
* Enter unique email "test.automation<TS>@test.com" in "Email" textbox
* Enter "123-456-7890" in "Phone Number" textbox
* Enter "Automation Challenge" in "Business Name" textbox
* Select "Online search" from how did you hear about drop down
* Check the conditions and terms checkbox
* Verify "Continue to pre-qualify" button is enabled
* Click "Continue to pre-qualify" button
* Verify the url contains "/apply/loan"
* Verify the button "Let's get started" is displayed
* Click "Let's get started" button
* Verify the url contains "apply/prequalify/financing_needs"
* Verify "Save & Continue" button is "disabled"
* Verify the option "I have a strict timeline: 2 weeks or less" is displayed
* Verify the option "I’m flexible: about a month works for me" is displayed
* Verify the option "I don’t have a specific time in mind" is displayed
* Click the option "I’m flexible: about a month works for me"
* Verify the button "Save & Continue" is displayed
* Verify "Save & Continue" button is enabled
* Click "Save & Continue" button
* Verify the url contains "/apply/prequalify/business/inception_date"
* Verify the option "Less than 2 years ago" is displayed
* Verify the option "2-5 years ago" is displayed
* Verify the option "6-10 years ago" is displayed
* Verify the option "More than 10 years ago" is displayed
* Verify the button "Save & Continue" is displayed
* Verify "Save & Continue" button is "disabled"
* Click the option "6-10 years ago"
* Verify "Save & Continue" button is enabled
* Click "Save & Continue" button
* Verify the url contains "/apply/prequalify/business/industry"