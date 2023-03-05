package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ConfigReader;
import utilities.DatabaseUtils;

import java.sql.ResultSet;

public class ViewAccountPage extends BasePage {

    @FindBy(id = "transactionTable_wrapper")
    WebElement transactionHistory;

    @FindBy(xpath = "//table[@id='transactionTable']//tbody/tr[1]/td[4]")
    WebElement transactionHistoryAmount;

    @FindBy(xpath = "//table[@id='transactionTable']//tbody/tr[1]/td[3]")
    WebElement transactionHistoryDescription;


    public void verifyAccountPage() {
        Assert.assertTrue("View Account Page is not displayed", transactionHistory.isDisplayed());
    }

    public void verifyTransactionHistory() {

        String actualAmountUI = transactionHistoryAmount.getText().replace("$", "");
        String expAmountFromCode = ConfigReader.getConfigProperty("transfer.amount");
        Assert.assertTrue("Transaction amount mismatch", expAmountFromCode.equals(actualAmountUI));

        System.out.println("============UI Testing===============");
        System.out.println("Actual Amount from UI: " + actualAmountUI);
        System.out.println("Exp Amount from Code: " + expAmountFromCode);

    }

    public void verifyDetailsIntoDatabase() {
        try {


            // We are getting TRn and Description form UI
            String descAndTrn = transactionHistoryDescription.getText();
            String TRN = descAndTrn.split("-")[0].replace(" (TRN)", "");
            String decsFromUI = descAndTrn.split("-")[1].trim();

            // Execute the query and store the result
            String query = "select * from account_transaction where transaction_number='"+TRN+"';";
            ResultSet rs = DatabaseUtils.executeQuery(query);
            rs.next();

            // Testing of amount from code with DB
            String expAmountFromCode = ConfigReader.getConfigProperty("transfer.amount");
            String actualAmountFromDB = rs.getString("amount");
            Assert.assertTrue("Amount is not updated into the DB", expAmountFromCode.equals(actualAmountFromDB));

            // Printing the output
            System.out.println("============DB Testing===============");
            System.out.println("Actual Amount from DB: " + actualAmountFromDB);
            System.out.println("Exp Amount from Code: " + expAmountFromCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
