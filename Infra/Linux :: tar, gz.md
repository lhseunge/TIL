# I. tar

`tar` 는 압축에 드는 시간이 적고 필요 리소스가 적지만, 용량은 거의 줄지 않는다. 

→ 여러 파일을 하나로 묶는데 의의를 둔다.

## 1) 압축

```bash
# tar -cvf [압축된 파일 이름.tar] [압축하려는 파일]

# ex) rawdata 폴더를 files.tar 파일로 압축
tar -cvf files.tar /data/rawdata
```

## 2) 해제

```bash
# tar -xvf [압축된 파일.tar]

# ex) files.tar 파일 압축 해제
tar -xvf files.tar
```

# II. tar.gz

`tar.gz` 는 일반적인 압축으로 용량이 많이 줄고, 리소스도 적게 사용한다. 

→ `tar` 파일을 압축하기 좋다.

## 1) 압축

```bash
# tar -zcvf [압축된 파일 이름.tar.gz] [압축하려는 파일]

# ex) rawdata 폴더를 files.tar.gz 파일로 압축
tar -zcvf files.tar.gz /data/rawdata
```

## 2) 해제

```bash
# tar -zxvf [압축된 파일.tar.gz]

# ex) files.tar.gz 파일 압축 해제
tar -zxvf files.tar.gz
```

---

https://doheejin.github.io/linux/2021/03/01/linux-tar.html
