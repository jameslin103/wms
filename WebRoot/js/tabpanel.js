/*
 * Compressed by JSA(www.xidea.org)
 */
TabPanel = function(_) {
	this.renderTo = _.renderTo || $(document.body);
	this.border = _.border;
	this.render = typeof this.renderTo == "string" ? $("#" + this.renderTo)
			: this.renderTo;
	this.widthResizable = _.widthResizable;
	this.heightResizable = _.heightResizable;
	this.autoResizable = _.autoResizable ? true : false;
	this.width = _.width || "100%";
	this.height = _.height || "100%";
	this.items = _.items;
	this.active = _.active || 0;
	this.tabs = [];
	this.scrolled = false;
	this.tabWidth = 110 + 4;
	this.fixNum = 2;
	this.scrollFinish = true;
	this.maxLength = _.maxLength || -1;
	this.maxzindex = 0;
	this.init()
};
TabPanel.prototype = {
	init : function() {
		var A = this;
		if (this.autoResizable) {
			this.widthResizable = this.heightResizable = true;
			this.render.css("overflow", "hidden");
			$(window).resize(function() {
				window.setTimeout(function() {
					A.resize()
				}, 200)
			})
		}
		this.render.width(this.width);
		this.render.height(this.height);
		var _ = this.border != "none" ? 2 : 0;
		this.tabpanel = $("<DIV></DIV>");
		this.tabpanel.addClass("tabpanel");
		this.tabpanel.width(this.render.width() - _);
		this.tabpanel.height(this.render.height() - _);
		this.render.append(this.tabpanel);
		this.tabpanel_tab_content = $("<DIV></DIV>");
		this.tabpanel_tab_content.addClass("tabpanel_tab_content");
		this.tabpanel_tab_content.appendTo(this.tabpanel);
		this.tabpanel_left_scroll = $("<DIV></DIV>");
		this.tabpanel_left_scroll.bind("click", function() {
			A.moveLeft()
		});
		this.tabpanel_left_scroll.addClass("tabpanel_left_scroll");
		this.tabpanel_left_scroll.addClass("display_none");
		this.tabpanel_left_scroll.bind("mouseover", function() {
			var _ = $(this);
			_.addClass("tabpanel_scroll_over");
			_.bind("mouseout", function() {
				_.unbind("mouseout");
				_.removeClass("tabpanel_scroll_over")
			})
		});
		this.tabpanel_left_scroll.appendTo(this.tabpanel_tab_content);
		this.tabpanel_right_scroll = $("<DIV></DIV>");
		this.tabpanel_right_scroll.bind("click", function() {
			A.moveRight()
		});
		this.tabpanel_right_scroll.addClass("tabpanel_right_scroll");
		this.tabpanel_right_scroll.addClass("display_none");
		this.tabpanel_right_scroll.bind("mouseover", function() {
			var _ = $(this);
			_.addClass("tabpanel_scroll_over");
			_.bind("mouseout", function() {
				_.unbind("mouseout");
				_.removeClass("tabpanel_scroll_over")
			})
		});
		this.tabpanel_right_scroll.appendTo(this.tabpanel_tab_content);
		this.tabpanel_move_content = $("<DIV></DIV>");
		this.tabpanel_move_content.addClass("tabpanel_move_content");
		this.tabpanel_move_content.appendTo(this.tabpanel_tab_content);
		this.tabpanel_mover = $("<UL></UL>");
		this.tabpanel_mover.addClass("tabpanel_mover");
		this.tabpanel_mover.appendTo(this.tabpanel_move_content);
		this.tabpanel_tab_spacer = $("<DIV></DIV>");
		this.tabpanel_tab_spacer.addClass("tabpanel_tab_spacer");
		this.tabpanel_tab_spacer.appendTo(this.tabpanel_tab_content);
		this.tabpanel_content = $("<DIV></DIV>");
		this.tabpanel_content.addClass("tabpanel_content");
		this.tabpanel_content.appendTo(this.tabpanel);
		var C = this.tabpanel.width(), B = this.tabpanel.height();
		if (this.border == "none")
			this.tabpanel.css("border", "none");
		this.tabpanel_tab_content.width(C);
		this.tabpanel_content.width(C);
		this.tabpanel_content.height(B
				- this.tabpanel_tab_content.get(0).offsetHeight);
		this.update();
		for ( var D = 0; D < this.items.length; D++) {
			this.items[D].notExecuteMoveSee = true;
			this.addTab(this.items[D])
		}
		if (this.active >= 0)
			this.show(this.active, false)
	},
	moveLeft : function() {
		if (this.scrollFinish) {
			this.disableScroll();
			this.scrollFinish = false;
			Fader.apply(this, new Array({
				element : this.tabpanel_mover,
				style : "marginLeft",
				num : this.tabWidth,
				maxMove : this.maxMove,
				onFinish : this.useableScroll
			}));
			this.run()
		}
	},
	moveRight : function() {
		if (this.scrollFinish) {
			this.disableScroll();
			this.scrollFinish = false;
			Fader.apply(this, new Array({
				element : this.tabpanel_mover,
				style : "marginLeft",
				num : this.tabWidth * -1,
				maxMove : this.maxMove,
				onFinish : this.useableScroll
			}));
			this.run()
		}
	},
	moveToLeft : function() {
		if (this.scrolled && this.scrollFinish) {
			this.disableScroll();
			this.scrollFinish = false;
			var $ = parseInt(this.tabpanel_mover.css("marginLeft")) * -1;
			Fader.apply(this, new Array({
				element : this.tabpanel_mover,
				style : "marginLeft",
				num : $,
				maxMove : this.maxMove,
				interval : 20,
				step : ($ / 10) < 10 ? 10 : $ / 10,
				onFinish : this.useableScroll
			}));
			this.run()
		}
	},
	moveToRight : function() {
		if (this.scrolled && this.scrollFinish) {
			this.disableScroll();
			this.scrollFinish = false;
			var _ = parseInt(this.tabpanel_mover.css("marginLeft")) * -1, B = this.tabpanel_mover
					.children().length
					* this.tabWidth, A = this.tabpanel_move_content.width(), $ = (B
					- A - _ + this.fixNum)
					* -1;
			Fader.apply(this, new Array({
				element : this.tabpanel_mover,
				style : "marginLeft",
				num : $,
				maxMove : this.maxMove,
				step : ($ * -1 / 10) < 10 ? 10 : $ * -1 / 10,
				onFinish : this.useableScroll
			}));
			this.run()
		}
	},
	moveToSee : function($) {
		if (this.scrolled) {
			var _ = this.tabWidth * $, A = parseInt(this.tabpanel_mover
					.css("marginLeft")), B;
			if (A <= 0) {
				B = (A + _) * -1;
				if (((B + A) * -1) >= this.maxMove)
					this.moveToRight();
				else {
					this.disableScroll();
					this.scrollFinish = false;
					Fader.apply(this, new Array({
						element : this.tabpanel_mover,
						style : "marginLeft",
						num : B,
						maxMove : this.maxMove,
						step : (B / 10) < 10 ? 10 : B / 10,
						onFinish : this.useableScroll
					}));
					this.run()
				}
			} else {
				B = (_ - A) * -1;
				if ((B * -1) >= this.maxMove)
					this.moveToRight();
				else {
					this.disableScroll();
					this.scrollFinish = false;
					Fader.apply(this, new Array({
						element : this.tabpanel_mover,
						style : "marginLeft",
						num : B,
						maxMove : this.maxMove,
						onFinish : this.useableScroll
					}));
					this.run()
				}
			}
		}
	},
	disableScroll : function() {
		this.tabpanel_left_scroll.addClass("tabpanel_left_scroll_disabled");
		this.tabpanel_left_scroll.attr("disabled", true);
		this.tabpanel_right_scroll.addClass("tabpanel_right_scroll_disabled");
		this.tabpanel_right_scroll.attr("disabled", true)
	},
	useableScroll : function() {
		var $ = this;
		if (this.scrolled)
			if (parseInt($.tabpanel_mover.css("marginLeft")) == 0) {
				$.tabpanel_left_scroll
						.addClass("tabpanel_left_scroll_disabled");
				$.tabpanel_left_scroll.attr("disabled", true);
				$.tabpanel_right_scroll
						.removeClass("tabpanel_right_scroll_disabled");
				$.tabpanel_right_scroll.removeAttr("disabled")
			} else if (parseInt($.tabpanel_mover.css("marginLeft")) * -1 == $.maxMove) {
				$.tabpanel_left_scroll
						.removeClass("tabpanel_left_scroll_disabled");
				$.tabpanel_left_scroll.removeAttr("disabled", true);
				$.tabpanel_right_scroll
						.addClass("tabpanel_right_scroll_disabled");
				$.tabpanel_right_scroll.attr("disabled")
			} else {
				$.tabpanel_left_scroll
						.removeClass("tabpanel_left_scroll_disabled");
				$.tabpanel_left_scroll.removeAttr("disabled", true);
				$.tabpanel_right_scroll
						.removeClass("tabpanel_right_scroll_disabled");
				$.tabpanel_right_scroll.removeAttr("disabled")
			}
		$.scrollFinish = true
	},
	update : function() {
		var $ = this.tabpanel_tab_content.width();
		if (this.scrolled)
			$ -= (this.tabpanel_left_scroll.width() + this.tabpanel_right_scroll
					.width());
		this.tabpanel_move_content.width($);
		this.maxMove = (this.tabpanel_mover.children().length * this.tabWidth)
				- $ + this.fixNum
	},
	showScroll : function() {
		var _ = this.tabpanel_mover.children().length * this.tabWidth, $ = this.tabpanel_tab_content
				.width();
		if (_ > $ && !this.scrolled) {
			this.tabpanel_move_content.addClass("tabpanel_move_content_scroll");
			this.tabpanel_left_scroll.removeClass("display_none");
			this.tabpanel_right_scroll.removeClass("display_none");
			this.scrolled = true
		} else if (_ < $ && this.scrolled) {
			this.moveToLeft();
			this.tabpanel_move_content
					.removeClass("tabpanel_move_content_scroll");
			this.tabpanel_left_scroll.addClass("display_none");
			this.tabpanel_right_scroll.addClass("display_none");
			this.scrolled = false;
			this.scrollFinish = true
		}
	},
	addTab : function(H) {
		if (this.maxLength != -1 && this.maxLength <= this.tabs.length) {
			alert("\u60a8\u53ea\u80fd\u6253\u5f00"
					+ this.maxLength
					+ "\u4e2a\u9009\u9879\u5361\uff0c\u8bf7\u5173\u95ed\u4e0d\u7528\u7684\u9009\u9879\u5361\u3002");
			return false
		}
		H.id = H.id || Math.uuid();
		if ($("#" + H.id).length > 0)
			this.show(H.id, false);
		else if (this.scrollFinish) {
			var A = this, D = $("<LI></LI>");
			D.attr("id", H.id);
			D.appendTo(this.tabpanel_mover);
			var B = $("<DIV></DIV>");
			B.text(H.title);
			B.appendTo(D);
			var C = H.closable == false ? 0 : 5;
			if (H.icon) {
				B.addClass("icon_title");
				B.css("background-image", "url(\"" + H.icon + "\")");
				if (B.width() > (this.tabWidth - 35 - C)) {
					B.width((this.tabWidth - 50 - C));
					B.attr("title", H.title);
					D.append("<DIV>...</DIV>")
				}
			} else {
				B.addClass("title");
				if (B.width() > (this.tabWidth - 19 - C)) {
					B.width((this.tabWidth - 30 - C));
					B.attr("title", H.title);
					D.append("<DIV>...</DIV>")
				}
			}
			var F = $("<DIV></DIV>");
			F.addClass("closer");
			F.attr("title", "\u5173\u95ed");
			F.appendTo(D);
			var _ = $("<DIV></DIV>");
			_.addClass("html_content");
			_.appendTo(this.tabpanel_content);
			var G = _.find("iframe"), E = this.tabpanel_mover.children().index(
					this.tabpanel_mover.find(".active")[0]);
			if (E < 0)
				E = 0;
			if (this.tabs.length > E)
				H.preTabId = this.tabs[E].id;
			else
				H.preTabId = "";
			H.tab = D;
			H.title = B;
			H.closer = F;
			H.content = _;
			H.disable = H.disable == undefined ? false : H.disable;
			H.closable = H.closable == undefined ? true : H.closable;
			if (H.closable == false)
				F.addClass("display_none");
			if (H.disabled == true) {
				D.attr("disabled", true);
				B.addClass(".disabled")
			}
			this.tabs.push(H);
			D.bind("click", function($) {
				return function() {
					A.show($, false)
				}
			}(this.tabs.length - 1));
			F.bind("click", function($) {
				return function() {
					A.kill($)
				}
			}(this.tabs.length - 1));
			if (H.closable)
				D.bind("dblclick", function($) {
					return function() {
						A.kill($)
					}
				}(this.tabs.length - 1));
			if (!H.lazyload)
				this.show(this.tabs.length - 1, H.notExecuteMoveSee);
			this.showScroll();
			this.update();
			if (!H.lazyload && !H.notExecuteMoveSee)
				this.moveToRight()
		}
	},
	getTabPosision : function($) {
		if (typeof $ == "string")
			for ( var _ = 0; _ < this.tabs.length; _++)
				if ($ == this.tabs[_].id) {
					$ = _;
					break
				}
		return $
	},
	flush : function($) {
		$ = this.getTabPosision($);
		if (typeof $ == "string")
			return false;
		else {
			var A = this.tabs[$].content.find("iframe");
			if (A.length > 0) {
				var _ = this.tabs[$].id + "Frame";
				this.iterateFlush(window.frames[_])
			}
		}
	},
	iterateFlush : function(A) {
		if (A.window.frames.length > 0) {
			for ( var _ = 0; _ < A.window.frames.length; _++)
				this.iterateFlush(A.window.frames[_])
		} else if (A.document.forms.length > 0) {
			for (_ = 0; _ < A.document.forms.length; _++) {
				try {
					A.document.forms[_].submit()
				} catch ($) {
					A.location.reload()
				}
			}
		} else
			A.location.reload()
	},
	show : function($, _) {
		if (this.tabs.length < 1)
			return false;
		$ = this.getTabPosision($);
		if (typeof $ == "string")
			$ = 0;
		if (this.scrollFinish) {
			if ($ >= this.tabs.length)
				$ = 0;
			this.tabs[$].content.css("z-index", ++this.maxzindex);
			if (this.tabs[$].tab.hasClass("active")) {
				if (!_)
					this.moveToSee($)
			} else {
				if (this.tabs[$].content.html() == "")
					this.tabs[$].content.html(this.tabs[$].html);
				this.tabpanel_mover.find(".active").removeClass("active");
				this.tabs[$].tab.addClass("active");
				if (!_)
					this.moveToSee($)
			}
		}
	},
	kill : function(_) {
		var $ = this;
		_ = this.getTabPosision(_);
		var B = this.tabs[_].preTabId;
		this.tabs[_].closer.remove();
		this.tabs[_].title.remove();
		this.tabs[_].tab.remove();
		this.tabs[_].content.remove();
		this.tabs.splice(_, 1);
		for ( var A = 0; A < this.tabs.length; A++) {
			this.tabs[A].tab.unbind("click");
			this.tabs[A].tab.bind("click", function(_) {
				return function() {
					$.show(_, false)
				}
			}(A));
			this.tabs[A].closer.unbind("click");
			this.tabs[A].closer.bind("click", function(_) {
				return function() {
					$.kill(_)
				}
			}(A));
			if (this.tabs[A].closable) {
				this.tabs[A].tab.unbind("dblclick");
				this.tabs[A].tab.bind("dblclick", function(_) {
					return function() {
						$.kill(_)
					}
				}(A))
			}
		}
		this.update();
		this.showScroll();
		this.show(B, false)
	},
	getTabsCount : function() {
		return this.tabs.length
	},
	setTitle : function($, _) {
		$ = this.getTabPosision($);
		if ($ < this.tabs.length)
			this.tabs[$].title.text(_)
	},
	getTitle : function($) {
		$ = this.getTabPosision($);
		return this.tabs[$].title.text()
	},
	setContent : function(_, $) {
		_ = this.getTabPosision(_);
		if (_ < this.tabs.length)
			this.tabs[_].content.html($)
	},
	getContent : function($) {
		$ = this.getTabPosision($);
		return this.tabs[$].content.html()
	},
	setDisable : function($, _) {
		$ = this.getTabPosision($);
		if ($ < this.tabs.length) {
			this.tabs[$].disable = _;
			if (_) {
				this.tabs[$].tab.attr("disabled", true);
				this.tabs[$].title.addClass(".disabled")
			} else {
				this.tabs[$].tab.removeAttr("disabled");
				this.tabs[$].title.removeClass(".disabled")
			}
		}
	},
	getDisable : function($) {
		$ = this.getTabPosision($);
		return this.tabs[$].disable
	},
	setClosable : function($, _) {
		$ = this.getTabPosision($);
		if ($ < this.tabs.length) {
			this.tabs[$].closable = _;
			if (_)
				this.tabs[$].closer.addClass("display_none");
			else {
				this.tabs[$].closer.addClass("closer");
				this.tabs[$].closer.removeClass("display_none")
			}
		}
	},
	getClosable : function($) {
		$ = this.getTabPosision($);
		return this.tabs[$].closable
	},
	getActiveIndex : function() {
		return this.tabpanel_mover.children().index(
				this.tabpanel_mover.find(".active")[0])
	},
	getActiveTab : function() {
		var $ = this.tabpanel_mover.children().index(
				this.tabpanel_mover.find(".active")[0]);
		if (this.tabs.length > $)
			return this.tabs[$];
		else
			return null
	},
	resize : function() {
		var $ = this.border == "none" ? 0 : 2;
		this.render.height(document.documentElement.clientHeight);
		if (this.widthResizable) {
			this.width = this.render.width();
			this.tabpanel.width(this.width - $);
			this.tabpanel_tab_content.width(this.width - $);
			this.tabpanel_content.width(this.width - $)
		}
		if (this.heightResizable) {
			this.height = this.render.height();
			this.tabpanel.height(this.height - $);
			this.tabpanel_content.height(this.height
					- this.tabpanel_tab_content.get(0).offsetHeight)
		}
		this.showScroll();
		this.useableScroll();
		this.update();
		var _ = this;
		setTimeout(function() {
			_.moveToSee(_.getActiveIndex())
		}, 200)
	},
	setRenderWH : function($) {
		if ($) {
			if ($.width != undefined)
				this.render.width($.width);
			if ($.height != undefined)
				this.render.height($.height);
			this.resize()
		}
	}
}