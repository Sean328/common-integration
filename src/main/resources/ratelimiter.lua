local nextPermissionTime = redis.call('GET', KEYS[1])
local currentTime = tonumber(ARGV[1])
local permitsPerSecond = tonumber(ARGV[2])
local permits = tonumber(ARGV[3])
local timeout = tonumber(ARGV[4])
local blockingTime = 0

if nextPermissionTime then
    nextPermissionTime = tonumber(nextPermissionTime)
    if nextPermissionTime > currentTime then
        blockingTime = nextPermissionTime - currentTime
        if timeout > 0 and blockingTime > timeout then
        return -1
        end
    else
        nextPermissionTime = currentTime
    end
else
    nextPermissionTime = currentTime
end

nextPermissionTime = nextPermissionTime + 1 / permitsPerSecond * permits * 1000
redis.call('SET', KEYS[1], nextPermissionTime)
return blockingTime