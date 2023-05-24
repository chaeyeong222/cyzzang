# 베이스 이미지
FROM node:16.18.1

# 이미지 내부 작업공간 설정
WORKDIR /home/ssafit-front

# package.json 복사 및 종속성 설치
COPY package.json .

# 소스 코드 복사
COPY . .

# 빌드 수행
CMD ["npm", "run", "serve"]