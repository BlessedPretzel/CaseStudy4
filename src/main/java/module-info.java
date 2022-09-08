module com.example.casestudy4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires org.apache.logging.log4j;


    opens se233 to javafx.fxml;
    exports se233.chapter4;
}