package com.jiuzhi.ad.rop.response;

import java.util.List;

public class AdVo {

	private int id;
	private String img;
	private int showtime;
	private long dt;
	private String url;
	private int isJump;
	private int open;
	private List<SlotVo> slot;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getShowtime() {
		return showtime;
	}

	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SlotVo> getSlot() {
		return slot;
	}

	public void setSlot(List<SlotVo> slot) {
		this.slot = slot;
	}

	public int getIsJump() {
		return isJump;
	}

	public void setIsJump(int isJump) {
		this.isJump = isJump;
	}

	public int getOpen() {
		return open;
	}

	public void setOpen(int open) {
		this.open = open;
	}

}
