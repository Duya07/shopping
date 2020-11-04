class randomUtil { }

//静态方法，获得随机数
randomUtil.getRandomNumber = (min, max) => parseInt(Math.random() * (max - min)) + min

export {
    randomUtil
}