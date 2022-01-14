package test.data;

import org.testng.annotations.DataProvider;
import test.data.objects.Account;

public class AccountData {
    @DataProvider(name = "Account Data")
    public static Object[][] dataForAccount() {
        return Account.getData("./src/test/resources/Json/SauceDemoJsonData.json");
    }
}
