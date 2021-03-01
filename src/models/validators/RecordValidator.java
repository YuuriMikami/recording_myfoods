package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Record;
import utils.DBUtil;

public class RecordValidator {
    public static List<String> validate(Record e, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String code_error = validateCode(e.getCode(), codeDuplicateCheckFlag);
        if(!code_error.equals("")) {
            errors.add(code_error);
        }

        String name_error = validateName(e.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(e.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // 登録番号
    private static String validateCode(String code, Boolean codeDuplicateCheckFlag){
    	if(code == null || code.equals("")){
    		return "登録番号を入力してください。";
    	}

    	//重複確認
    	if(codeDuplicateCheckFlag){
    		EntityManager em = DBUtil.createEntityManager();
    		long records_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class).setParameter("code", code).getSingleResult();
    		em.close();
            if(records_count > 0) {
                return "入力された登録番号の情報はすでに存在しています。";
            }
        }

        return "";
    }

    //氏名入力確認
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

 // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
