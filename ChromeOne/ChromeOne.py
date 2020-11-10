from selenium import webdriver
import time
import json
import sys

LOG_ERR = "ERR"
LOG_INFO = "INFO"
DEFAULT_TEMPLATE_PATH = "./template.json"

class ChromeOne:
    def __init__(self):
        self.load_config_info()
        self.register()
        actions = self.config["actions"]
        for e in actions:
            self.log(LOG_INFO, str(e))
            self.log(LOG_INFO, "do " + e["type"] + " action start")
            self.action_refs[e["type"]](e)
            self.log(LOG_INFO, "do " + e["type"] + " action end")
        while True:
            time.sleep(0)
    
    def load_config_info(self):
        argv = sys.argv
        argv_len = len(argv)
        if argv_len > 1:
            self.config_file_path =  str(argv[1])
        else:
            self.config_file_path =  DEFAULT_TEMPLATE_PATH
        with open(self.config_file_path, 'r', encoding='utf-8') as load_f:
            self.config = json.load(load_f)
        self.log(LOG_INFO, "load template info from " + self.config_file_path + " success")
    
    def log(self, level, info):
        print("[" + level + "] " + self.now()  + " " + info)

    def now(self):
        return time.strftime("%Y-%m-%d %H:%M:%S")
    
    def register(self):
        self.action_refs = {
            "open_browser": self.do_open_browser_action,
            "open_page": self.do_open_page_action,
            "input": self.do_input_action,
            "delay": self.do_delay_action,
            "click": self.do_click_action,
            "close_browser": self.do_close_browser_action
        }
        self.log(LOG_INFO, "register actions " + str(self.action_refs.keys()) + " success")
    
    def do_open_browser_action(self, action):
        opt = webdriver.chrome.options.Options()
        opt.add_argument('--no-sandbox')                # 解决DevToolsActivePort文件不存在的报错
        opt.add_argument('window-size=1920x3000')       # 设置浏览器分辨率
        opt.add_argument('--disable-gpu')               # 谷歌文档提到需要加上这个属性来规避bug
        opt.add_argument('--hide-scrollbars')           # 隐藏滚动条，应对一些特殊页面
        opt.add_argument('blink-settings=imagesEnabled=false')      # 不加载图片，提升运行速度
        opt.add_argument('--headless')                  # 浏览器不提供可视化界面。Linux下如果系统不支持可视化不加这条会启动失败
        # opt.binary_location = r"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" # 手动指定使用的浏览器位置
        # self.browser = webdriver.Chrome(options=opt)
        self.browser = webdriver.Chrome()
    
    def do_open_page_action(self, action):
        page_url = action["content"]
        self.browser.get(page_url)
        self.log(LOG_INFO, "page url：" + page_url)

    def do_input_action(self, action):
        key = action["key"]
        content = action["content"]
        input = self.browser.find_element_by_css_selector(key)
        input.clear()
        input.send_keys(content)

    def do_delay_action(self, action):
        content = action["content"]
        self.log(LOG_INFO, "delay " + content + " s")
        time.sleep(int(content))

    def do_click_action(self, action):
        key = action["key"]
        button = self.browser.find_element_by_css_selector(key)
        button.click()
    
    def do_close_browser_action(self, action):
        self.browser.close()

if __name__ == "__main__":
    ChromeOne()