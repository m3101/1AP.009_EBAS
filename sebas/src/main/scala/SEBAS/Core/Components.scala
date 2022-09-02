package SEBAS.core

class Situation

class Effect

class Agent[ActionType<:Action[EffectType,_],EffectType<:Effect,SituationType<:Situation]{
    var situation:SituationType
    def be_affected(e:EffectType):Agent[ActionType,EffectType,SituationType] = ???
    def decide():ActionType = ???
}

class Action[EffectType<:Effect,MediaType<:Medium[_,EffectType]]{
    def happen(m:Set[MediaType]):EffectType = ???
}

class Plan[ActionType<:Action[_,MediumType],MediumType<:Medium[_,_]]{
    var media:Set[MediumType]
    def schedule(a:ActionType):Plan[ActionType,MediumType] = ???
    def resolve():Plan[ActionType,MediumType] = ???
}

class Medium[AgentType<:Agent[_,EffectType,_],EffectType<:Effect]{
    var agents:Set[AgentType] = Set.empty
    def effect(e:EffectType):Medium[AgentType,EffectType] = {
        agents = agents.map(a=>a.be_affected(e))
    }
}
