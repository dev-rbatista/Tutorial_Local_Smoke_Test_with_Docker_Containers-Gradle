Vagrant.configure("2") do |config|
  config.vm.box = "generic/ubuntu1804"

  config.vm.network "private_network", ip: "192.168.30.30"
  config.vm.network "forwarded_port", guest: 8082, host: 8082

  config.vm.provider "virtualbox" do |vb|

  vb.gui = false
  vb.cpus = 2
  vb.memory = "2048"


  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install iputils-ping -y
    sudo apt-get install -y avahi-daemon libnss-mdns
    sudo apt-get install -y unzip
    sudo apt-get install openjdk-8-jdk-headless -y
    # ifconfig
  SHELL

  end

end