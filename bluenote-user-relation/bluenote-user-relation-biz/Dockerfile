# syntax=docker/dockerfile:1-labs
# ============================================================
# bluenote-user-relation Dockerfile
# Build context: 仓库根目录
# 构建命令: docker build -t bluenote-user-relation -f bluenote-user-relation/bluenote-user-relation-biz/Dockerfile .
# ============================================================

FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /build

# 递归复制所有层级的 pom.xml，保留原始目录结构（--parents 防止 BuildKit glob 扁平化覆盖）
COPY --parents **/pom.xml ./

# 阶段 1：安装 framework + 所有 *-api 模块到本地仓库，使阶段 2 解析上游依赖时走 .m2 而非 reactor 源码
COPY bluenote-framework/ ./bluenote-framework/
COPY bluenote-user/bluenote-user-api/src ./bluenote-user/bluenote-user-api/src
COPY bluenote-oss/bluenote-oss-api/src ./bluenote-oss/bluenote-oss-api/src
COPY bluenote-kv/bluenote-kv-api/src ./bluenote-kv/bluenote-kv-api/src
COPY bluenote-distributed-id-generator/bluenote-distributed-id-generator-api/src ./bluenote-distributed-id-generator/bluenote-distributed-id-generator-api/src
COPY bluenote-note/bluenote-note-api/src ./bluenote-note/bluenote-note-api/src
COPY bluenote-user-relation/bluenote-user-relation-api/src ./bluenote-user-relation/bluenote-user-relation-api/src
COPY bluenote-count/bluenote-count-api/src ./bluenote-count/bluenote-count-api/src
COPY bluenote-comment/bluenote-comment-api/src ./bluenote-comment/bluenote-comment-api/src

RUN --mount=type=cache,target=/root/.m2 \
    mvn install -DskipTests -B -am -pl bluenote-framework/bluenote-common,bluenote-framework/bluenote-spring-boot-starter-biz-operationlog,bluenote-framework/bluenote-spring-boot-starter-biz-context,bluenote-framework/bluenote-spring-boot-starter-rpc,bluenote-user/bluenote-user-api,bluenote-oss/bluenote-oss-api,bluenote-kv/bluenote-kv-api,bluenote-distributed-id-generator/bluenote-distributed-id-generator-api,bluenote-note/bluenote-note-api,bluenote-user-relation/bluenote-user-relation-api,bluenote-count/bluenote-count-api,bluenote-comment/bluenote-comment-api

# 阶段 2：仅复制目标服务源码并构建。上游依赖已在阶段 1 安装到 .m2。
COPY bluenote-user-relation/bluenote-user-relation-api/src ./bluenote-user-relation/bluenote-user-relation-api/src
COPY bluenote-user-relation/bluenote-user-relation-biz/src ./bluenote-user-relation/bluenote-user-relation-biz/src

RUN --mount=type=cache,target=/root/.m2 \
    mvn -pl bluenote-user-relation/bluenote-user-relation-biz package -DskipTests -B

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /build/bluenote-user-relation/bluenote-user-relation-biz/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
