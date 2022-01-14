package test.data.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static core.helper.JsonParser.convertListJsonToObject2d;

@Getter
@Setter
public class Account {
    @SerializedName("account")
    private String account;
    @SerializedName("password")
    private String password;
    @SerializedName("productId")
    private String productId;

    public Account() {
        super();
    }

    @Override
    public String toString() {
        return account + "," + password + "," + productId;
    }

    public static Object[][] getData(String filePath) {
        Object[][] object2d = null;
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            List<Account> accounts = new Gson().fromJson(reader, new TypeToken<List<Account>>() {
            }.getType());
            object2d = convertListJsonToObject2d(accounts);
            // close reader
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return object2d;
    }
}
