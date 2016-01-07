import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by Павел on 13.11.2015.
 */
    @WebServlet("/login")
    public class TryLogin extends HttpServlet{
        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            JsonReader reader = Json.createReader(req.getInputStream());
            JsonObject newJson = reader.readObject();
            reader.close();

            String username = newJson.getString("login");
            String password = newJson.getString("password");

            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            Main dbHelper = new Main();
            out.print(dbHelper.checkUser(username, password));
        }
}