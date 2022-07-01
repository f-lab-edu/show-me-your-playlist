import MySQLdb
import requests
from bs4 import BeautifulSoup

 #  'GN0900':'Pop'   
 # 'GN1400':'Fork/Blues/Country'    
genres = {'GN0100':'Korean Ballad', 'GN0200':'Korean Dance', 'GN0300':'Korean Hip-hop', 'GN0400':'Korean R&B/Soul', 
          'GN0500':'Korean Indi', 'GN0600':'Korean Rock/Metal', 'GN0700':'Korean Teuroteu', 'GN0800':'Korean Fork/Blues', 
         'GN1000':'Rock', 'GN1100':'Electronic', 'GN1200':'Hip-hop', 'GN1300':'R&B/Soul'}
i=1
for key, value in genres.items():
    gnrCode = key
    genre = value
    
    header = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko'}
    melon_url = "https://www.melon.com/genre/song_list.htm?gnrCode=" + gnrCode + "&steadyYn=Y"
    raw = requests.get(melon_url, headers = header)
    soup = BeautifulSoup(raw.text, "html.parser")

    box = soup.find("tbody")
        
    all_singer = box.find_all("div", {"class":"ellipsis rank02"})
    all_title = box.find_all("div", {"class":"ellipsis rank01"})

    singers = []
    titles = []

    for s in all_singer:
        singers.append(s.find('a').text)
        
    for t in all_title:
        titles.append(t.find('a').text)

 
    items=[]
    for item in zip(singers, titles):
        item = list(item)
        item.insert(1, genre)
        items.append(tuple(item))

    print(items)

    conn = MySQLdb.connect( 
        user="root", 
        passwd="5679", 
        host="localhost", 
        db="playlist" 
        # charset="utf-8" 
    )
    
    cursor = conn.cursor() 
    
    for item in items:
        cursor.execute( 
            f"INSERT INTO music VALUES({i}, \"{item[0]}\", \"{item[1]}\", \"{item[2]}\")")
        i += 1
        
    # 커밋하기 
    conn.commit() 
    # # 연결종료하기 
    conn.close()





