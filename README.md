<h1>Objective</h1>
<p>- This aplication aims to send mails by a verified user to anyone using <strong>SMTP</strong></p>
<p>- <strong>Mailgun</strong> website is used as a tool for sending mails and tracking the events of any sent mail</p>
<p>- The user can track the events of any mail sent by mailgun like (SENT,FAILED,DELIVRED,OPENED,......) and accurate datetime for each event</p>
<p>- If there are any links with the body of the mail , user can track the link if the link clicked or not and how many times the reciever click the link</p>
<br/>
<h1>Way Of Installation</h1>
<p>- You should clone the project from github using this link <strong>https://github.com/Khalidabdellatif187/mail-sender.git</strong></p>
<p>- After cloning , please create a postgresql database called <strong>mail_sender</strong> and ensure that it has <strong>public</strong> schema</p>
<p>- After you create the database , you can run the project and liquibase sql files will update database </p>
<p>- After that you can open your browser and go to <strong>http://localhost:8099/ui</strong></p>
<p>- Kindly note that you should register first before you signin</p>
<p>- After you registred successfully,you can signin to get an access and start to send mails</p>
