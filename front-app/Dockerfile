# 베이스 이미지
FROM node:16.18.1

# 이미지 내부 작업공간 설정 
WORKDIR /app

COPY ./frontend/package.json .

RUN npm install

COPY ./frontend .

CMD ["npm", "run", "serve"]