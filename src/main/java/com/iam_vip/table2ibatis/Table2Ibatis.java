/**
 * 
 */
package com.iam_vip.table2ibatis;

import java.io.InputStream;
import java.util.List;

import com.google.gson.Gson;
import com.iam_vip.table2ibatis.logic.DBAndTable;
import com.iam_vip.table2ibatis.logic.IbatisPropertiesWithFolder;
import com.iam_vip.table2ibatis.logic.TableDefine;
import com.iam_vip.table2ibatis.logic.TableDefine.ITableBeanName;
import com.iam_vip.table2ibatis.rule.NameRulePrefixNoUnderline;

/**
 * @author Colin
 */
public class Table2Ibatis {

	/**
	 * 
	 */
	public Table2Ibatis() {
	}


	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Table2Ibatis run = new Table2Ibatis();

		IbatisPropertiesWithFolder first = new IbatisPropertiesWithFolder();

		/// 读取配置文件并初始化 ///
		InputStream in = run.getClass().getClassLoader().getResourceAsStream("table2-ibatis.properties");
		first.initProperties(in);
		in.close();

		// first.printProperties();
		/// 创建文件夹 ///
		first.initFolders();
		/// 复制模版文件 ///
		first.cpTemplate();



		DBAndTable dbt = new DBAndTable(first);
		// dbt.printTypeMap();

		// ITableBeanName rule = new NameRuleNoUnderline();
		ITableBeanName rule = new NameRulePrefixNoUnderline("");
		List<TableDefine> list = dbt.getTables();

		/// 生成 Bean ///
		first.toModel(list, rule);
		/// 生成业务逻辑接口 ///
		first.toInterface(list, rule);
		/// 生成逻辑实现 ///
		first.toImplement(list, rule);
		/// 生成 XML 文件 ///
		first.toXML(list, rule);
		// ///
		first.toPhoneRequest(list, rule);



		Gson gson = new Gson();
		list.forEach(tb -> {
			System.out.println(gson.toJson(tb));
		});

	}

}
