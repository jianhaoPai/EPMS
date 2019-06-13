package com.epms.Service;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.epms.Bean.Menu;

public interface MenuService {

	public List<Menu> findMenuByRole(int jobId);
}
