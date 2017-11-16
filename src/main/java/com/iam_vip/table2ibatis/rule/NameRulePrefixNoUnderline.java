/**
 * 
 */
package com.iam_vip.table2ibatis.rule;

import com.iam_vip.table2ibatis.logic.TableDefine.ITableBeanName;

/**
 * @author Colin
 */
public class NameRulePrefixNoUnderline implements ITableBeanName {

	private String prefix;

	/**
	 * 
	 */
	public NameRulePrefixNoUnderline(String prefix) {
		this.prefix = prefix;
	}

	private String zeroToUpper(String str) {
		return (str.charAt(0) + "").toUpperCase() + str.substring(1).toLowerCase();
	}

	@Override
	public String translateIt(String tableName) {
		tableName = tableName.replace(prefix, "");
		String[] arr = tableName.split("_");
		StringBuffer buf = new StringBuffer(tableName.length() + 8);
		for (String str : arr) {
			buf.append(zeroToUpper(str));
		}
		return buf.toString();
	}

	@Override
	public String replaceIt(String text) {
		return text.replace(prefix, "");
	}

}
