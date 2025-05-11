/*P367*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Scanner;

public class SqlTest {

	public static void main(String[] args) {

		System.out.println("プログラム開始");

		String url = "jdbc:mariadb://localhost:3306/db_company";
		String user = "root";
		String password = "";		
		
		
		try(Connection connection = DriverManager.getConnection(url, user, password)) {

			System.out.println("プログラム1ではテスト社員1を部門ID1で登録します。よろしいですか？（y/n）");	
			Scanner scanner = new Scanner(System.in);
			String message = scanner.nextLine();
			scanner.close();
			if (message.equals("n")) {
				System.out.println("プログラム1終了");	  
			}
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employees values(?,?,?)");//SQL文をPreparedStatementオブジェクトに収納
			int idValue = 1;
			String nameValue = "テスト社員1";
			int department_idValue = 1;

			//INSERT前にチェックを行う	
			String prechecksql1 = "select * from employees";
			PreparedStatement preparedStatement11 = connection.prepareStatement(prechecksql1);
			ResultSet precheckrs = preparedStatement11.executeQuery();
            while(precheckrs.next()) {
            	int prersid = precheckrs.getInt("employees.id");
            	String prersname = precheckrs.getString("employees.name");
            	if(prersid == idValue || prersname == nameValue) {
        			System.out.println("idまたは名前が重複しています");
        			return;
            	}
             }
			
			
			
			
			//入力予定のデータを確認する	
			System.out.println("idValueは" + idValue);	
			System.out.println("nameValueは" + nameValue);
			System.out.println("department_idValueは" + department_idValue);
			preparedStatement.setInt(1, idValue);
			preparedStatement.setString(2, nameValue);
			preparedStatement.setInt(3, department_idValue);
			ResultSet rs = preparedStatement.executeQuery();
			
        
			//結果を確認する	
			String checksql1 = "select * from employees";
			PreparedStatement preparedStatement12 = connection.prepareStatement(checksql1);
			ResultSet checkrs = preparedStatement12.executeQuery();
            while(checkrs.next()) {
            	int rsid = checkrs.getInt("employees.id");
            	String rsname = checkrs.getString("employees.name");
            	int rsdid = checkrs.getInt("employees.department_id");
            	System.out.println("現在行のデータはid=" + rsid + " name=" + rsname + " department_id=" + rsdid + "です。");
            }
            
            rs.close();
			System.out.println("プログラム1終了");	


			//insert文で追加
			PreparedStatement preparedStatement2 = connection.prepareStatement("insert into employees values(?,?,?)");//SQL文をPreparedStatementオブジェクトに収納
			nameValue = "テスト社員2";
			preparedStatement2.setString(1, nameValue);

			System.out.println("プログラム2終了");		


			
			
			//select文で件数を呼び出す
			String sql = "select * from employees";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql);
			preparedStatement3.executeQuery();
			
			//selectの結果を利用する
			ResultSet resultSet = preparedStatement3.executeQuery();
			while(resultSet.next()) {
				System.out.println(resultSet.getInt("id"));	
				System.out.println(resultSet.getString("name"));	
				System.out.println(resultSet.getInt("department_id"));	
			}
			System.out.println("プログラム3終了");				
		
//クローズ処理は自動なので明示的に記載する必要なし		
		
		} catch (SQLNonTransientConnectionException e) {
			System.out.println("DB未起動エラー");
			e.printStackTrace();
		} catch (SQLInvalidAuthorizationSpecException e) {
			System.out.println("DBユーザーまたはパスワードが違います。");
			e.printStackTrace();
		}/* catch (SQLException e) {
			System.out.println("その他のDB接続エラー");
			e.printStackTrace();
		}*/ catch (SQLException e) {

			if (e.getErrorCode() == 1062) {
			System.out.println("id重複エラー");
			} else {
				System.out.println("その他のDB接続エラー"+e.getErrorCode());
			}
			e.printStackTrace();
		}
	

	}
}

