package devandroid.jeff.controledeprodutos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    public static final String NOME_DB = "DB_APP";
    public static final String TB_PRODUTO = "TB_PRODUTO";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_PRODUTO
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " estoque INTEGER NOT NULL, " +
                " valor DOUBLE NOT NULL); ";

        try{
            db.execSQL(sql);
        }catch (Exception e){
            Log.i("ERROR", "Erro ao criar tabela: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}