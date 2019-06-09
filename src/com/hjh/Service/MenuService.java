package com.hjh.Service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.hjh.Bean.Menu;

public interface MenuService {

	public List<Menu> findMenuByRole(int jobId);
}
