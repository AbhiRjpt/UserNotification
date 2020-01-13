# UserNotification

#Install tomcat8
#Import project
#Install Maven
#Java version 1.8
#MySQL version 5.7+
#SpringBoot Config

#Modules:

# commons-module: It has all files with are need in both UserServiceApi and UserEventNotification module.
#DaoTemplate has been Implemented to Fetch, Insert, Update, Delete operations.
#InternalException and InternalErrorCode has been handled.
#Rest Client has been used to communicate one server to another
#DataBaseConnectionPool used for connection Pooling.


#  UserServiceApi
# API: /user/userEvent
#Method: Post Mapping 
# Request Data : will accept UserEventRequestData, Which will contain bill and feedback data for user and send back response.
#Based on the noun and verb the event have been decided and triggered.
#Notifications will be triggered based on the rules mentioned in Req doc.





# User-event-notification module:
# It has it's own startup file and also while initializing it will also bind data object in config which are necessary.
# UserNotificationController class contains Api's
#        TO Send Notification
#   API /notification/push 
#  Method: POST Mapping
# Request Data: SendPushNotificationRequestData which contain specific parameters to send notification using firebase.
#In User Table for FCM Token for user should be in UserData Table;


#    To Test Create Tables:

#UserData table: to store UserData
/*
CREATE TABLE `UserData` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `userkey` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pwd_hash` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `emailid` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `devicePlatformName` varchar(246) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deviceIdentity` varchar(246) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `registerDateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deviceToken` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fcmToken` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  `birthDate` datetime DEFAULT NULL,
  `userLanguage` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `city` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `appVersion` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dob` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT '0',
  `deactivatedDate` datetime DEFAULT NULL,
  `testUser` tinyint(1) DEFAULT '0',
  `isOperator` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`userid`,`userkey`),
  UNIQUE KEY `userid_UNIQUE` (`userkey`),
  KEY `idx_User_userid` (`userkey`) USING BTREE,
  KEY `idx_User_fname` (`fname`) USING BTREE,
  KEY `idx_User_lname` (`lname`) USING BTREE,
  KEY `fk_user_deviceId` (`deviceIdentity`) USING BTREE,
  KEY `fk_user_isdel` (`isdeleted`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
*/
 
 
 #To Store User Bill Payment Data
 
 /*
 CREATE TABLE `UserBillPaymentData` (
   `userBillPaymentId` int(11) NOT NULL AUTO_INCREMENT,
   `userid` int(11) NOT NULL,
   `noun` varchar(10) DEFAULT NULL,
   `ts` varchar(55) NOT NULL,
   `latlong` varchar(45) DEFAULT NULL,
   `verb` varchar(10) DEFAULT NULL,
   `timespent` int(11) DEFAULT NULL,
   `bank` varchar(45) DEFAULT NULL,
   `merchantid` int(11) DEFAULT NULL,
   `paymentValue` double(10,2) DEFAULT NULL,
   `paymentMode` varchar(15) DEFAULT NULL,
   PRIMARY KEY (`userBillPaymentId`)
 ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */


#To Store User Feedback Data

CREATE TABLE `UserPaymetFeedBackData` (
  `userPaymentFeedbackId` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `userBillPaymentId` int(11) DEFAULT NULL,
  `noun` varchar(10) DEFAULT NULL,
  `ts` varchar(55) NOT NULL,
  `latlong` varchar(45) DEFAULT NULL,
  `verb` varchar(10) DEFAULT NULL,
  `timespent` int(11) DEFAULT NULL,
  `userFeedbackText` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userPaymentFeedbackId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


#INSERT Data into tables and remember to have FCM_Token,because it's necessary to send notification